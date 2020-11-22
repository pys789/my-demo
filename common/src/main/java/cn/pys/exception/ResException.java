package cn.pys.exception;

/**
 * @Description
 * @Date 2020/11/12 15:30
 * @Created by pengys
 */
public class ResException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public ResException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ResException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ResException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ResException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
