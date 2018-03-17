package main.model;

/**
 * Created by zuoyafei on 2018/3/16.
 *
 * 用于后端和前端的消息传递对象，code代表本次请求处理成功与否，msg为请求返回的成功或失败的信息，data是后端需要传递给前端的数据
 */
public class Response<T> {
    private static int successCode = 0;
    private static int failCode = 1;
    private Integer code;
    private String msg;
    private T data;

    public Response() {
        this.code = successCode;
        this.msg = "success";
    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public void setTrue() {
        this.code = successCode;
        this.msg = "success";
    }

    public void setFalse() {
        this.code = failCode;
        this.msg = "failed";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Response{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
