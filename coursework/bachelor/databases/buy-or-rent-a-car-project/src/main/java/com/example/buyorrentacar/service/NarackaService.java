package com.example.buyorrentacar.service;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Naracka;

import java.time.LocalDate;
import java.util.List;

public interface NarackaService {
    List<Naracka> listAll();
    Naracka create(LocalDate datum, String iznos, boolean status,boolean platena, Long covekId,Long kupuvacId, Long avtomobilId);
    Naracka update(Long id);
    Naracka plati(Long id);
    void delete(Long id);
    List<Naracka> listByIdNaSopstvenikAndStatus(Long id,boolean status);
    List<Naracka> listByIdNaKupuvacAndStatus(Long id,boolean status);
    Naracka findById(Long id);
}
