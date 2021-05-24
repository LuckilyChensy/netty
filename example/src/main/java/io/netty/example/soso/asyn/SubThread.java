package io.netty.example.soso.asyn;

/**
 * @Description:
 * @Date:Created in 22:24 2021/5/24
 * @Author：soso
 */
public class SubThread extends Thread {

    private RequestFuture request;

    public SubThread(RequestFuture request){
        this.request = request;
    }


    @Override
    public void run() {
        Response resp = new Response();

        resp.setId(request.getId());
        resp.setResult("server response"+Thread.currentThread().getId());

        //子线程模拟睡眠1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //此处返回响应结果给主线程
        RequestFuture.received(resp);

    }
}
