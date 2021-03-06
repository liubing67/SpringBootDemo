package com.abing.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        try {
            client1();
            client2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void client1()throws IOException {
        System.out.println("TCPClient1 Start...");
        //1.创建TCP客户端Socket服务
        Socket client=new Socket();
        //2.与服务端进行连接
        InetSocketAddress address = new InetSocketAddress("10.33.250.199",10004);
        client.connect(address);

        //3.连接成功后获取客户端Socket输出流
        OutputStream outputStream = client.getOutputStream();

        //4.通过输出流往服务端写入数据
        outputStream.write("Hello my name is Client1".getBytes());

        //5.告诉服务端发送完毕
        client.shutdownOutput();

        //6.读取服务端返回数据
        InputStream is = client.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));

        //7.关闭流
        client.close();
    }

    public static void client2()throws IOException{
        System.out.println("TCPClient2 Start...");

        //1.创建TCP客户端Socket服务
        Socket client = new Socket();


        //2.与服务端进行连接
        InetSocketAddress address = new InetSocketAddress("10.33.250.199",10004);
        client.connect(address);

        //3.连接成功后获取客户端Socket输出流
        OutputStream outputStream = client.getOutputStream();

        //4.通过输出流往服务端写入数据
        outputStream.write("Hello my name is Client2".getBytes());

        //5.告诉服务端发送完毕
        client.shutdownOutput();

        //6.读取服务端返回数据
        InputStream is = client.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));

        //7.关闭流
        client.close();
    }
}
