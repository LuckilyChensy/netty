package io.netty.example.soso.asyn;

import java.net.ResponseCache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Date:Created in 21:54 2021/5/24
 * @Author：soso
 */
public class RequestFuture {

    public static Map<Long,RequestFuture> futures = new ConcurrentHashMap<>();

    private long id;

    private String path;

    private Object request;

    private Object result;

    private long timeout = 5000;
    // 自增ID
    private static final AtomicLong aid = new AtomicLong(1);

    public RequestFuture(){
        id = aid.incrementAndGet();
        addFuture(this);
    }

    public static void addFuture(RequestFuture future){
        futures.put(future.getId(),future);
    }

    /**同步获取响应结果*/
    public Object get(){

        synchronized(this){
            while(this.result == null){
                try{
                    // 主线默认等待 1s, 然后再查看是否获取结果
                    this.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return this.result;

    }
    /**异步线程返回结果给主线程*/
    public static void received(Response resp){
        RequestFuture future = futures.remove(resp.getId());
        // 设置响应结果
        if(future!=null){
            future.setResult(resp.getResult());
            /**通知主线程*/
            synchronized (future){
                future.notify();
            }
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
