package com.example.buyorrentacar.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Subselect("SELECT * FROM public.detalen_prikaz_na_selektiran_avtomobil")
@Immutable
public class detalen_prikaz_na_selektiran_avtomobil {

    private String marka;

    private String model;

    private String menuvac;

    private String boja;

    private String kilometraza;

    private String tip_gorivo;

    private String cena;
    @Id
    @Column(name = "id")
    private Long korisnik_id;

}
