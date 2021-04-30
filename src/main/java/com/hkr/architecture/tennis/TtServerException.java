package com.hkr.architecture.tennis;


/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-12-28 11:27
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 服务端引发的异常可以使用
 */
public class TtServerException extends TtRuntimeException {

    private static final long serialVersionUID = -256755294469277125L;

    public TtServerException() {
        super(GlobalExceptionCode.SERVER_SIDE_EXCEPTIONS, String.format("[%s]", GlobalExceptionCode.SERVER_SIDE_EXCEPTIONS_MSG));
    }

    public TtServerException(int code) {
        super(code, String.format("[%s]", GlobalExceptionCode.SERVER_SIDE_EXCEPTIONS_MSG));
    }

    public TtServerException(String description) {
        super(GlobalExceptionCode.SERVER_SIDE_EXCEPTIONS, String.format("%s", description));

    }

    public TtServerException(int code, String description) {
        super(code, String.format("%s", description));
    }

    public TtServerException(String description, Throwable cause) {
        super(String.format("%s", description), cause);
    }


    public TtServerException(int code, String description, Throwable cause) {
        super(code, String.format("%s", description), cause);
    }

}
