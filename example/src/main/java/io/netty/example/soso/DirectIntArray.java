package io.netty.example.soso;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description:
 * 使用 Unsafe 来实现一个直接内存实现的 int 数组
 * @Date:Created in 22:11 2021/5/22
 * @Author：soso
 */
public class DirectIntArray {

    private static final int INT = 4;
    private long size;
    private long address;

    private static Unsafe unsafe;

    static{
        try{
            // Unsafe类有权限访问控制，只能通过反射获取其实例
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe)f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DirectIntArray(long size){
        this.size = size;
        address = unsafe.allocateMemory(size*INT);
    }

    public int get(long i){
        if(i > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return unsafe.getInt(address + i*INT);
    }

    public void set(long i,int value){
        if(i >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        unsafe.putInt(address+i*INT,value);
    }

    public long size(){
        return size;
    }

    public void freeMemory(){
        unsafe.freeMemory(address);
    }

    public static void main(String[] args){
        DirectIntArray array = new DirectIntArray(4);
        array.set(0,1);
        array.set(1,2);
        array.set(2,3);
        array.set(3,4);

        int sum = 0;

        for(int i = 0;i < array.size();i++){
            sum += array.get(i);
        }

        System.out.println(sum);
        // 最后一定不要忘记释放内存
        array.freeMemory();
    }


}
