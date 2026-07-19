package com.example.buyorrentacar.model.exceptions;

public class InvalidCovekImeException extends RuntimeException{
    public InvalidCovekImeException(Long id) {
        super(String.format("Ne postoi Covek so id %d",id));
    }
}
