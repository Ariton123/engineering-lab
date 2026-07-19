package com.example.buyorrentacar.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException()
    {
        super("Invalid User!");
    }
}
