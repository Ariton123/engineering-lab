package com.example.buyorrentacar.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super(String.format("User with id %d does not exist!",id));
    }
}