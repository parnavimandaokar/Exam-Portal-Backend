package com.exam.helper;

public class UserNotFoundException extends  Exception {
    public UserNotFoundException(){
        super("User not Found");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
