package practice.IO.bio;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 传统socket服务端
 * 单线程只能连接一个客户端
 * @author -琴兽-
 *
 */
public class OioServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		//只允许3个客户端进来
		ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3);
		//创建socket服务,监听10101端口
		ServerSocket server=new ServerSocket(9090);
		System.out.println("服务器启动！");
		while(true){
			//获取一个套接字（阻塞）
			final Socket socket = server.accept();
			System.out.println("来个一个新客户端！");
			newCachedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					//业务处理
					handler(socket);
				}
			});
			
		}
	}
	
	/**
	 * 读取数据
	 * @param socket
	 * @throws Exception
	 */
	public static void handler(Socket socket){
			try {
				byte[] bytes = new byte[1024];
				InputStream inputStream = socket.getInputStream();
				
				while(true){
					//读取数据（阻塞）
					int read = inputStream.read(bytes);
					if(read != -1){
						System.out.println(new String(bytes, 0, read));
					}else{
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					System.out.println("socket关闭");
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
