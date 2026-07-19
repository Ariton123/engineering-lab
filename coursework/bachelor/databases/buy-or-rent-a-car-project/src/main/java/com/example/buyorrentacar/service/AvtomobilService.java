package com.example.buyorrentacar.service;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;

import java.util.List;
import java.util.Optional;

public interface AvtomobilService {
    List<Avtomobil> listAll();
    List<Avtomobil> listByUserId(Long id);

    Optional<Avtomobil> findById(Long id);

    Avtomobil create(String marka,
                     String model,
                     String menuvac,
                     String boja,
                     Integer kilometraza,
                     Integer godinaProizvodstvo,
                     String tipGorivo,
                     String kategorija,
                     Integer cena,
                     Long covekId);
    Avtomobil update(Long id,
                    String marka,
                     String model,
                     String menuvac,
                     String boja,
                     Integer kilometraza,
                     Integer godinaProizvodstvo,
                     String tipGorivo,
                     String kategorija,
                     Integer cena);
    void deleteById(Long id);
}
