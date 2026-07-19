package com.example.buyorrentacar.model.exceptions;


public class PassworDoNotMatchException extends RuntimeException{

    public PassworDoNotMatchException()
    {
        super("Passwords dont match eachother!");
    }
}
