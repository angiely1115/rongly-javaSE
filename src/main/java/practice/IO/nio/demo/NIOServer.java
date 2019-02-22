package practice.IO.nio.demo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

	
	private int port = 8080;
	private  InetSocketAddress address = null;
	//选择器，多路复用器
	private Selector selector;
	
	public NIOServer(int port){
		try{
			this.port = port;
			address = new InetSocketAddress(this.port);
			
			//要想富，先修路
			//高速公路修起来
			ServerSocketChannel server = ServerSocketChannel.open();
			server.bind(address);
			//默认为阻塞，手动设置为非阻塞 不设置就是阻塞的
			server.configureBlocking(false);
			
			//大管家开始工作,开门营业
			selector = Selector.open();
			
			//Option的简称
			server.register(selector, SelectionKey.OP_ACCEPT);
			
			System.out.println("服务器准备就绪，监听端口是：" + this.port);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void listen(){
		try{
			//轮询
			while(true){
				//有多少个人在服务大厅排队
				int wait = this.selector.select();
				System.out.println("有多少人在排队:"+wait);
				if(wait == 0){ continue;}
				//获取正在请求排队的人
				Set<SelectionKey> keys = this.selector.selectedKeys();
				System.out.println("正在排队请求的人："+keys);
				Iterator<SelectionKey> i = keys.iterator();
				while(i.hasNext()){
					SelectionKey key = i.next();
					//对排队的人处理那么的业务
					process(key);
					//处理完了就释放 可以回去了
					i.remove();
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void process(SelectionKey key) throws Exception{
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//是否可以接收
		if(key.isAcceptable()){
			ServerSocketChannel server = (ServerSocketChannel)key.channel();
			SocketChannel client = server.accept();
			client.configureBlocking(false);//设置成非阻塞 不设置就是阻塞的
			client.register(selector, SelectionKey.OP_READ);//
		}
		//是否可以读
		else if(key.isReadable()){
			SocketChannel client = (SocketChannel)key.channel();
			
			int len = client.read(buffer);
			if(len > 0){
				buffer.flip();
				String content = new String(buffer.array(), 0, len);
				System.out.println(content);
				client.register(selector, SelectionKey.OP_WRITE);
			}
			buffer.clear();
			//client.finishConnect();
		}
		//是否可以写
		else if(key.isWritable()){
			SocketChannel client = (SocketChannel)key.channel();
			
			client.write(buffer.wrap("Hello Wold".getBytes()));
			
			client.close();
		}
	}
	
	
	public static void main(String[] args) {
		new NIOServer(8080).listen();
	}
	
}
