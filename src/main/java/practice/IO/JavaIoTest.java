package practice.IO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: lvrongzhuan
 * @Description:IO流
 * @Date: 2018/5/28 19:09
 * @Version: 1.0
 * modified by:
 */
public class JavaIoTest {
    /**
     * 字节流测试
     */
    @Test
    public void inputStreamTest(){
        try(FileInputStream fileInputStream = new FileInputStream("D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\a.txt")){
            int available = fileInputStream.available();
            System.out.println("可用字节数："+available);
            byte[] bytes = new byte[available];
            int len =0;
            while ((len = fileInputStream.read(bytes))!=-1){
                System.out.println(new String (bytes,0,len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void outputStreamTest(){
        String fileInPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\a.txt";
        String fileOutPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\b.txt";
        try(FileInputStream fileInputStream = new FileInputStream(fileInPath);
            FileOutputStream fileOutputStream = new FileOutputStream(fileOutPath)){
            System.out.println("可用字节数："+fileInputStream.available());
            byte[] bytes = new byte[10];
            int len = 0;
            while ((len=fileInputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fileReader(){
        String fileInPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\a.txt";
        try(FileReader fileReader = new FileReader(fileInPath)){
            System.out.println("字符编码:"+fileReader.getEncoding());
            char[] chars = new char[1024];
            int len = 0;
            while ((len=fileReader.read(chars))!=-1){
                System.out.println(new String(chars,0,len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节缓冲流测试
    @Test
    public void bufferedStreamTest(String fileInPath, String fileOutPath){
//        String fileInPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\a.txt";
//        String fileOutPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\b.txt";
        long begin = System.currentTimeMillis();
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInPath));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOutPath))
        ) {
            System.out.println("可用字节数："+bis.available());
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len=bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bufferByteCopy耗时:"+(System.currentTimeMillis()-begin));
    }

    @Test
    public void bufferedReaderTest(){
        String fileInPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\a.txt";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInPath))) {
            String str = null;
            while ((str=bufferedReader.readLine())!=null){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bufferedWriteTest(){
        String fileOutPath = "D:\\resource\\workspace\\lvStudy\\JavaSE_Practice\\b.txt";
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOutPath,true))) {
            bufferedWriter.write("肤白貌美大长腿");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传统IO文件拷贝
     */
    private void fileCopyByFiles(String path1,String path2){
       Path path =  Paths.get(path1);
        Path topath = Paths.get(path2);
        long begin = System.currentTimeMillis();
        try {
            Files.copy(path,topath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FileCopy耗时："+(System.currentTimeMillis()-begin));
    }
    /**
     * 通道文件拷贝
     */
    private void fileCopyByChannel(String path1,String path2){
        long begin = System.currentTimeMillis();
        try(FileInputStream fileInputStream = new FileInputStream(path1);
            FileOutputStream fileOutputStream = new FileOutputStream(path2)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            FileChannel toFileChannel = fileOutputStream.getChannel();
            long count = fileChannel.size();
            fileChannel.transferTo(0,count,toFileChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileCopyByChannel耗时："+(System.currentTimeMillis()-begin));
    }

    /**
     * 通过open来打开通道
     * @param path1
     * @param path2
     */
    private void fileCopyByChannel2(String path1,String path2){
        //感觉最慢也还好 性能都差不多
        long begin = System.currentTimeMillis();
        try{
            FileChannel fileChannel =  FileChannel.open(Paths.get(path1), StandardOpenOption.READ);
            FileChannel toFileChannel = FileChannel.open(Paths.get(path2),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
            long count = fileChannel.size();
            fileChannel.transferTo(0,count,toFileChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileCopyByChannel耗时："+(System.currentTimeMillis()-begin));
    }

    @Test
    public void fileCopyByFiles1(){
        String path = "F:\\BaiduNetdiskDownload\\3分布式专题\\03.分布式专题\\3.1.分布式服务治理-dubbo(上)-.mp4";
        String  topath ="F:\\BaiduNetdiskDownload\\1.mp4";
        this.fileCopyByFiles(path,topath);
    }
    //小文件
    @Test
    public void fileCopyByFilesSmall(){
        String path = "F:\\BaiduNetdiskDownload\\nio\\13. 尚硅谷_NIO_Pipe 管道.avi";
        String  topath ="F:\\BaiduNetdiskDownload\\1.avi";
        this.fileCopyByFiles(path,topath);
    }



    @Test
    public void buffereByteCopyTest(){
        String path = "F:\\BaiduNetdiskDownload\\3分布式专题\\03.分布式专题\\3.1.分布式服务治理-dubbo(上)-.mp4";
        String  topath ="F:\\BaiduNetdiskDownload\\1.mp4";
        this.bufferedStreamTest(path,topath);
    }
    @Test
    public void buffereByteCopyTestSmall(){
        String path = "F:\\BaiduNetdiskDownload\\nio\\13. 尚硅谷_NIO_Pipe 管道.avi";
        String  topath ="F:\\BaiduNetdiskDownload\\1.avi";
        this.bufferedStreamTest(path,topath);
    }
    @Test
    public void fileCopyByChannelTest(){
        String path = "F:\\BaiduNetdiskDownload\\3分布式专题\\03.分布式专题\\3.1.分布式服务治理-dubbo(上)-.mp4";
        String  topath ="F:\\BaiduNetdiskDownload\\2.mp4";
        this.fileCopyByChannel2(path,topath);
    }

    @Test
    public void fileCopyByChannelTestSmall(){
        String path = "F:\\BaiduNetdiskDownload\\nio\\13. 尚硅谷_NIO_Pipe 管道.avi";
        String  topath ="F:\\BaiduNetdiskDownload\\1.avi";
        this.fileCopyByChannel(path,topath);
    }

    /**
     * path测试
     */
    @Test
    public void testPath() {
        Path path = Paths.get("D:/uu");
        System.out.println(path);
       File file =  path.toFile();

        System.out.println(file.exists());
        file.mkdir();
        path.getFileName();
    }
}
