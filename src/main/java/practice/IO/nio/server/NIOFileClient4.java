package practice.IO.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: lvrongzhuan
 * @Description: NIO文件传输客户端
 * @Date: 2018/12/22 10:30
 * @Version: 1.0
 * modified by:
 */
public class NIOFileClient4 {
    static Selector selector;
    private static Charset charset = Charset.forName("UTF-8");
    /**
     * 发送文件给服务端
     * @param port
     */
    public static SocketChannel uploadFileToServer(int port){
        try {
            //打开连接通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(port)) ;
            System.out.println("默认是否阻塞："+socketChannel.isBlocking());
            //设置为阻塞
            socketChannel.configureBlocking(false);

            //打开选择器
             selector = Selector.open();
            //将通道注册到选择器上
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            return socketChannel;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向服务器写东西
     * @param socketChannel
     */
    public static void write(SocketChannel socketChannel) throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\Administrator\\Desktop\\pdf\\QQ视频20181114181444.mp4"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(byteBuffer)>0){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 读取服务器返回的信息
     */
    public static void read() throws IOException {
        while (true){
           int sel = selector.select();
           if(sel==0){
               continue;
           }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterable = selectionKeys.iterator();
           while (iterable.hasNext()){
               SelectionKey selectionKey = iterable.next();
               System.out.println("selectionKey:"+selectionKey);
               if(selectionKey.isReadable()){
                 SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                 ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                   String content = "";
                 while (socketChannel.read(byteBuffer)>0){
                     byteBuffer.flip();
                     content+=charset.decode(byteBuffer);
                     byteBuffer.clear();
                 }
                   System.out.println("接收服务端返回信息："+content);
                   selectionKeys.remove(selectionKey);
               }
           }
        }
    }

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = uploadFileToServer(9090);
        write(socketChannel);
        read();
    }
}
