package com.abing.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receive()throws IOException{

        System.out.println("Server Start...");

        //创建服务端Socket并明确端口号
        ServerSocket serverSocket = new ServerSocket(10004);
        while (true){
            //获取到客户端的Socket
            final Socket socket = serverSocket.accept();
            //通过线程执行客户端请求
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //通过客户端的Socket获取到输入流
                        InputStream is = socket.getInputStream();

                        //通过输入流获取到客户端传递的数据
                        byte[] bytes = new byte[1024];
                        int len = is.read(bytes);
                        System.out.println("server  receive  "+new String(bytes,0,len));

                        //将客户端发来的数据原封不动返回
                        OutputStream os = socket.getOutputStream();
                        os.write(new String(bytes,0,len).getBytes());
                        //关闭连接
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
