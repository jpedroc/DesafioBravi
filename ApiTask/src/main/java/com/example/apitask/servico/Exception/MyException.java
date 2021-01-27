package com.example.apitask.servico.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyException extends RuntimeException {

    public MyException(final String mensagem) {
        this(mensagem, null);
    }

    public MyException(final String message, final Throwable motivo) {
        super(message, motivo);
    }


}
