package com.smart_contact.smart_contact.helper;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(){
        super("user not found");
    }
}
