package practice.IO.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: lvrongzhuan
 * @Description: 创建一个接收文件NIO的服务端
 * @Date: 2018/12/22 9:27
 * @Version: 1.0
 * modified by:
 */

public class NIOFileServer4 {
    public static Selector startServer(int port){
        //获取通道
        try {
            ServerSocketChannel serverSocketChannel =  ServerSocketChannel.open();
            //设置成非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            //获取选择器
            Selector selector = Selector.open();
            //将通道注册到选择器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端启动完毕,可以接收连接了");
            return selector;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 监听选择器上的事件并处理
     * @param selector
     */
    public static void listenerHandel(Selector selector) throws IOException {
        //轮询获取选择器上已经就绪的事件，只有是大于0的表示已经就绪
        while(true){
           int sel = selector.select();
           if(sel==0){
               continue;
           }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                if(selectionKey.isAcceptable()){
                    System.out.println("客户端已经连接进来了");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    //接收客户端的连接
                    SocketChannel socketChannel =serverSocketChannel.accept();
                    //设置成非阻塞模式
                    System.out.println("接收是否阻塞："+serverSocketChannel.isBlocking());
                    socketChannel.configureBlocking(false);
                    //注册读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //用完就走了
                    selectionKeyIterator.remove();
                    //读事件就绪
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)  selectionKey.channel();
                    System.out.println("读是否阻塞："+socketChannel.isBlocking());
                    FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\Administrator\\Desktop\\QQ视频1.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                   while(socketChannel.read(byteBuffer)>0){
                       byteBuffer.flip();
                       fileChannel.write(byteBuffer);
                       byteBuffer.clear();
                   }
                    ByteBuffer byteBuffer1 = ByteBuffer.wrap("服务端已经接收完毕".getBytes());
                    socketChannel.write(byteBuffer1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Selector selector = startServer(9090);
        listenerHandel(selector);
    }

}
