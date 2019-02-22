package practice.IO.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * NIOServer：通信服务类
 * 单线程可以处理多个客户端
 * @author Sam
 *
 */
public class NIOServer2 {
	// 服务端的 Channel的通道类型
	private ServerSocketChannel serverSocketChannel;
	int port = 8080;
	// 多路注册复用器 注册SelectKey.OP_各种操作事件
	private Selector selector;
	// 定义接收缓冲池 和发送缓冲池 capacity
	ByteBuffer recBuffer = ByteBuffer.allocate(1024);
	ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
	// 消息缓存队列
	Map<SelectionKey, String> essiomMsg = new HashMap<SelectionKey, String>();
	// 缓存客户端编号机制
	Map<SelectionKey, Integer> clientMsg = new HashMap<SelectionKey, Integer>();
	// 初始化的客户端编号
	private static int client_no = 19056;

	public NIOServer2(int port) throws IOException {
		this.port = port;
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);//设置成非阻塞
		//打开选择器
		selector = Selector.open();
		// 告诉多路复用注册器 我已经初始化
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("【系统消息提示】 NIO消息服务器初始化完毕，可以随时接受客户端链接，监听端口为： " + this.port);
	}

	// 对我们selector上面的注册通道事件进行监听
	private void listener() throws IOException {
		// 不断去监听
		while (true) {
			//是否有线程在排队
			int eventCount = selector.select();
			/*selector.select(1000);//不阻塞 多少秒返回
			selector.selectNow();//不阻塞
			selector.wakeup();//不阻塞 唤醒selector*/
			if (eventCount == 0) {
				continue;
			}
			// selectedKeys :拥有Channel引用
			Set<SelectionKey> keys = selector.selectedKeys();
			final Iterator<SelectionKey> iterator = keys.iterator();
			while (iterator.hasNext()) {
				// 处理模块
				process(iterator.next());
				iterator.remove();
			}
		}

	}

	// nio框架的处理模块
	private void process(SelectionKey key) {
		SocketChannel socketChannel = null;
		try {
			// 异常下面 连接不到 避免不必要的异常抛出
			if (key.isValid() && key.isAcceptable()) {
				//接收就绪获取客户端连接
				socketChannel = serverSocketChannel.accept();
				++client_no;
				// 关键点一：非阻塞模式的改变
				socketChannel.configureBlocking(false);
				// 通过注册到selector上面告诉给服务 我需要你读取我传进来信息包
				socketChannel.register(selector, SelectionKey.OP_READ);
				System.out.println("接收准备读取..............");
			} else if (key.isValid() && key.isReadable()) {
				// 服务端从SocketChannel读取客户端发送过来的信息
				recBuffer.clear();
				// key 和我们的通道是有引用
				socketChannel = (SocketChannel) key.channel();
				// len =512
				int len = socketChannel.read(recBuffer);
				if (len > 0) {
					String msg = new String(recBuffer.array(), 0, len);
					// 放入消息缓存队列
					essiomMsg.put(key, msg);
					System.out.println(
							"当前处理线程ID:" + Thread.currentThread().getId() + "  读取来自客户端编号：" + client_no + " 信息为：" + msg);
					// 客户端显现我们要精准定位
					clientMsg.put(key, client_no);
					// 控制灵敏度
					socketChannel.register(selector, SelectionKey.OP_WRITE);
					System.out.println("服务端接收客户端读完又准备写........");
				}
			} else if (key.isValid() && key.isWritable()) {
				if (!essiomMsg.containsKey(key)) {
					return;
				}
				socketChannel = (SocketChannel) key.channel();
				sendBuffer.clear();
				sendBuffer.put((essiomMsg.get(key) + "您好，Sam老师已经处理完成你的请求！").getBytes());
				// 读写切换 切换到读模式
				sendBuffer.flip();
				// 写信息
				socketChannel.write(sendBuffer);
				System.out.println("当前处理线程ID:" + Thread.currentThread().getId() + "  对客户端编号：" + client_no + "写出信息为："
						+ essiomMsg.get(key) + "您好，Sam老师已经处理完成你的请求");
				// 再注册 实际上就是驱动下一个业务的发生
				socketChannel.register(selector, SelectionKey.OP_READ);
			}
		} catch (IOException e) {
			// 防止客户端非法下线
			key.cancel();
			try {
				// 又里到外的关闭思想
				socketChannel.socket().close();
				socketChannel.close();
				System.out.println("【系统消息提示】 客户端： " + clientMsg.get(key) + " 已经非法下线！");
				clientMsg.remove(key);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			new NIOServer2(8080).listener();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
