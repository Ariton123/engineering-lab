package com.example.buyorrentacar.model.exceptions;

public class InvalidUserEmailException extends RuntimeException{
    public InvalidUserEmailException(String message) {
        super(String.format("User with this email: %s does not exist!",message));
    }
}
