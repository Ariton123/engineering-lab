package com.example.buyorrentacar.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Subselect("SELECT * FROM public.pregled_naracki")
@Immutable
public class pregled_naracki {
    @Id
    private Long id;

    private boolean status;

    private LocalDate datum;

    private String iznos;

    private String ime;

    private String prezime;

    private String telefBroj;

    private String marka;

    private String model;

}
