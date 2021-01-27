package com.example;

public class CustomException extends RuntimeException{

    public CustomException(String mensagem) {
        super(mensagem);
    }
}
