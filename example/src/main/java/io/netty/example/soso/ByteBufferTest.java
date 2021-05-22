package io.netty.example.soso;

import java.nio.ByteBuffer;

/**
 * @Description:
 * @Date:Created in 22:45 2021/5/22
 * @Author：soso
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        // 创建一个堆内存实现的 byteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(12);
        // 写入值
        buffer.putInt(1);
        buffer.putInt(2);
        buffer.putInt(3);
        // 切换为读模式
        buffer.flip();
        // 读取值
        System.out.println(buffer.getInt());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getInt());

    }

}
