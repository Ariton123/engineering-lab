package com.example.buyorrentacar.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Entity
@Table(name = "naracki")
public class Naracka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;

    private String iznos;

    private boolean status;

    private boolean platena;
    @ManyToOne
    private Covek covek;

    @ManyToOne
    private Covek kupuvac;

    @OneToOne
    private Avtomobil avtomobil;

    public Naracka(){}
    public Naracka(LocalDate datum, String iznos, boolean status,boolean platena, Covek covek,Covek kupuvac, Avtomobil avtomobil) {
        this.datum = datum;
        this.iznos = iznos;
        this.status = status;
        this.platena = platena;
        this.covek = covek;
        this.kupuvac = kupuvac;
        this.avtomobil = avtomobil;
    }
}
