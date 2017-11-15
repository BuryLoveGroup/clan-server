package com.clan.exception;

/**
 * Created by robot on 2017/11/11.
 */
public class ParamException extends AppException {

    private static Integer state = 1414;

    public ParamException() {
        super(state);
    }

    public ParamException(String message) {
        super(state, message);
    }
}
