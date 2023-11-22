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

    int code; //自定义的错误代码
    public CustomException(int code,String message){
        super(message);
        this.code=code;
    }
}
