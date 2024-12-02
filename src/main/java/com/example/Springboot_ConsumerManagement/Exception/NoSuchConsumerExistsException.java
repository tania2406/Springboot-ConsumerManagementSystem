package com.example.Springboot_ConsumerManagement.Exception;

public class NoSuchConsumerExistsException extends RuntimeException
{
    String message;
    public NoSuchConsumerExistsException(){}

    public NoSuchConsumerExistsException(String message) {
        super(message);
        this.message = message;
    }
}
