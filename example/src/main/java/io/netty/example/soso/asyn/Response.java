package io.netty.example.soso.asyn;

/**
 * @Description:
 * @Date:Created in 22:11 2021/5/24
 * @Author：soso
 */
public class Response {

    private long id;

    private Object result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
