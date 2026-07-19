package com.example.buyorrentacar.model.exceptions;

public class InvalidAvtomobilIdxception extends RuntimeException{
    public InvalidAvtomobilIdxception(Long id) {
        super(String.format("Avtomobil with this id %d does not exist!",id));
    }
}
