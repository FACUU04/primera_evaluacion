package com.academy.back.exceptions;

public class NoEncontradoException extends RuntimeException{
    public NoEncontradoException(String mensage){
        super(mensage);
    }
}
