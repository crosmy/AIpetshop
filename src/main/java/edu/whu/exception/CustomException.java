package edu.whu.exception;

import lombok.Data;

@Data
public class CustomException extends Exception{
    //定义各种错误代码常量
    public final static int INPUT_ERROR = 100;
    public final static int UNAUTHORIZED = 101;
    public final static int USER_ALREADY_EXISTS = 102;
    public final static int USER_NOT_FOUND = 103;
    public final static int POST_NOT_COMPLETE = 104;
    public static final int POST_NOT_FOUND = 105;
    public static final int QUERY_ERROR = 106;
    public static final int CREATE_FAILED = 107;
    public static final int TRANSACTION_NOT_FOUND = 108;
    public static final int UPDATE_FAILED = 109;
    public static final int SEND_FAILED = 110;
    public static final int NO_CONVERSATION_FOUND = 111;
    public static final int DATABASE_ERROR = 112;
    public static final int VALIDATION_ERROR = 113;

    int code; //自定义的错误代码
    public CustomException(int code,String message){
        super(message);
        this.code=code;
    }
}
