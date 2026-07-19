package com.example.buyorrentacar.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Subselect("SELECT * FROM public.pregled_naracki")
@Immutable
public class prikaz_avtomobili_kupuvanje_rentanje {

    @Id
    private Long id;

    private String marka;

    private String model;

    private String kategorija;

    private String ime;

    private String prezime;

}
