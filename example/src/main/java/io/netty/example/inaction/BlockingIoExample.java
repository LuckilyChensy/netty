package io.netty.example.inaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author： bugest
 * @Description:
 *  1. 该代码只能同时处理一个连接 ,要管理多个并发客户端,需要为每个客户端 Socket 创建一个新的 Thread
 *  2. CPU分配时间片给这些线程后, 如果轮到该线程执行时, 该线程没有需要处理的请求, 则该时间片内资源浪费
 *  3. 在任何时候都可能有大量的线程处于休眠状态
 *  4. 每个线程的调用栈都分配内存, 其默认值大小区间是 64KB 到 1MB
 *  5. 当有大量线程的时候, 创建,销毁,切换都是开销
 * @Date:Created in 20:29 2021/4/13
 * @Modified By: 阻塞 I/O 示例
 */
public class BlockingIoExample {

    public void serve(int portNumber) throws IOException{

        //创建一个新的 ServerSocket，用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // 调用到该 accept() 方法的线程被阻塞, 直到一个连接建立
        // 响应请求后 accept(), 请求的数据会被封装成一个 Socket 实例对象
        Socket clientSocket  = serverSocket.accept();

        // 在请求过来前,下列的代码是不会被执行的
        // 从一个字符输入流中读取文本
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // 打印对象的格式化的表示到文本输出流
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

        String request;
        String response;

        // readLine() 方法将被阻塞,直到读到一个由换行符或者回车符结尾的字符串被读取
        while((request = in.readLine()) != null){
            if("Done".equals(request)){
                break;
            }
            // 请求被传递给服务器的处理方法
            response = processRequest(request);

            // 服务器的响应被发送给了客户端
            out.println(response);
        }
    }

    private String processRequest(String request){
        return "Processed";
    }

}
