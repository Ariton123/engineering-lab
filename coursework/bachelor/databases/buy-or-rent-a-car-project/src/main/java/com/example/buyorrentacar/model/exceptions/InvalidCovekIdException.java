package com.example.buyorrentacar.model.exceptions;

public class InvalidCovekIdException extends RuntimeException{
    public InvalidCovekIdException(Long id) {
        super(String.format("User with this id: %d was not found!",id));
    }
}
