package com.reachauto.hkr.tennis;

import com.reachauto.hkr.exception.GlobalExceptionCode;
import com.reachauto.hkr.exception.HkrServerException;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-12-28 11:27
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 请求参数异常
 */
public class TennisUtilException extends HkrServerException {


    private static final long serialVersionUID = -6164729939471924058L;

    public TennisUtilException() {
        super(GlobalExceptionCode.APP_SIDE_PARAMETER_EXCEPTION, GlobalExceptionCode.APP_SIDE_PARAMETER_EXCEPTION_MSG);
    }

    public TennisUtilException(int code) {
        super(code);
    }

    public TennisUtilException(String msg) {
        super(GlobalExceptionCode.APP_SIDE_PARAMETER_EXCEPTION, msg);
    }

    public TennisUtilException(int code, String description) {
        super(code, description);
    }

    public TennisUtilException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TennisUtilException(int code, String description, Throwable cause) {
        super(code, description, cause);
    }

}
