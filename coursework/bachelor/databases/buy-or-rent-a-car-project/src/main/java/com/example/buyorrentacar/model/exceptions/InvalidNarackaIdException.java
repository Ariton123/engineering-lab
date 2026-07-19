package com.example.buyorrentacar.model.exceptions;

public class InvalidNarackaIdException extends RuntimeException{
    public InvalidNarackaIdException(Long message) {
        super(String.format("Ne postoi naracka so id %d",message));
    }
}
