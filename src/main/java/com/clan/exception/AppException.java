package com.clan.exception;

/**
 * Created by robot on 2017/11/11.
 */
public class AppException extends Exception{

    private static Integer state = 500;

    public AppException(Integer state){
        super(String.valueOf(state));
    }
    public AppException(Integer state, String message){
        super(message);
    }
}
