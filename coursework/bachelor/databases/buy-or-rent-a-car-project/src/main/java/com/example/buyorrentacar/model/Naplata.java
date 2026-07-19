package com.example.buyorrentacar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "naplata")
public class Naplata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iznos;
    @OneToOne
    private Naracka naracka;


    public Naplata(){}
    public Naplata(String iznos, Naracka naracka) {
        this.iznos = iznos;
        this.naracka = naracka;
    }
}
