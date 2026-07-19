package com.example.buyorrentacar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "avtomobili")
public class Avtomobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marka;

    private String model;

    private String menuvac;

    private String boja;

    private Integer kilometraza;

    private Integer godinaProizvodstvo;

    private String tipGorivo;

    private String kategorija;

    private Integer cena;
    @ManyToOne
    private Covek covek;


    public Avtomobil(){}
    public Avtomobil(String marka, String model, String menuvac, String boja, Integer kilometraza, Integer godinaProizvodstvo, String tipGorivo, String kategorija, Integer cena, Covek covek) {
        this.marka = marka;
        this.model = model;
        this.menuvac = menuvac;
        this.boja = boja;
        this.kilometraza = kilometraza;
        this.godinaProizvodstvo = godinaProizvodstvo;
        this.tipGorivo = tipGorivo;
        this.kategorija = kategorija;
        this.cena = cena;
        this.covek = covek;
    }
}
