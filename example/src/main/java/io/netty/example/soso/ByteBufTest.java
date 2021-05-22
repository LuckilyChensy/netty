package io.netty.example.soso;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * @Description:
 * @Date:Created in 23:49 2021/5/22
 * @Author：soso
 */
public class ByteBufTest {

    public static void main(String[] args) {

        // 1. 参数是 preferDirect 即是否偏向于使用直接内存
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);

        // 2. 创建一个非池化基于堆内存的 ByteBuf
        ByteBuf byteBuf = allocator.heapBuffer();

        // 写入数据
        byteBuf.writeInt(1);
        byteBuf.writeInt(2);
        byteBuf.writeInt(3);

        // 读取数据
        System.out.println(byteBuf.readInt());
        System.out.println(byteBuf.readInt());
        System.out.println(byteBuf.readInt());

    }

}
