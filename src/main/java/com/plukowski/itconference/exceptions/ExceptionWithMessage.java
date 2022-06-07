package com.plukowski.itconference.exceptions;

public class ExceptionWithMessage extends Exception{
    private String message;

    public ExceptionWithMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
