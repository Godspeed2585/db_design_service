package com.wk.warehouse.exception;

/**
 * SysException类
 * @author wk
 * @version 1.0 2022/03/09
 * @description 系统异常
 */
public class SysException extends  RuntimeException{

    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
