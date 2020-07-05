package com.benz.web.exception;

public class DataNotFoundException  extends RuntimeException{

    public DataNotFoundException(String msg)
    {
        super(msg);
    }
}
