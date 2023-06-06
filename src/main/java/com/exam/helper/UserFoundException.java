package com.exam.helper;

public class UserFoundException extends  Exception {
    public UserFoundException(){
        super("User with Username allready exists");
    }

    public UserFoundException(String msg){
        super(msg);
    }
}
