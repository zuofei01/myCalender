package main.exception;

/**
 * Created by zuoyafei on 2018/3/15.
 */
public class MyException extends RuntimeException{
    private static final long serialVersionUID = 8002283590289218255L;
    private String msg;

    public MyException() {
    }
    public MyException(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
