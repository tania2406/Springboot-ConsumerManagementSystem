package com.example.Springboot_ConsumerManagement.Exception;

public class ConsumerAlreadyExistsException extends RuntimeException
{
    String message;
    public ConsumerAlreadyExistsException(){}

    public ConsumerAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
