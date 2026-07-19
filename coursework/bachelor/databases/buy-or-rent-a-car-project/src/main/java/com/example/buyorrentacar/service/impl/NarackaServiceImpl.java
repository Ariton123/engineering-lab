package com.example.buyorrentacar.service.impl;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Naracka;
import com.example.buyorrentacar.model.exceptions.*;
import com.example.buyorrentacar.repository.AvtomobilRepository;
import com.example.buyorrentacar.repository.CovekRepository;
import com.example.buyorrentacar.repository.NarackaRepository;
import com.example.buyorrentacar.service.NarackaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NarackaServiceImpl implements NarackaService {

    private final NarackaRepository narackaRepository;
    private final CovekRepository covekRepository;
    private final AvtomobilRepository avtomobilRepository;

    public NarackaServiceImpl(NarackaRepository narackaRepository, CovekRepository covekRepository, AvtomobilRepository avtomobilRepository) {
        this.narackaRepository = narackaRepository;
        this.covekRepository = covekRepository;
        this.avtomobilRepository = avtomobilRepository;
    }

    @Override
    public Naracka create(LocalDate datum, String iznos, boolean status,boolean platena, Long covekId,Long kupuvacId, Long avtomobilId) {
        Covek kupuvac1 = this.covekRepository.findById(kupuvacId).orElseThrow(()->new InvalidCovekIdException(kupuvacId));
        Covek covek1 = this.covekRepository.findById(covekId).orElseThrow(()->new InvalidCovekIdException(covekId));
        Avtomobil avtomobil = this.avtomobilRepository.findById(avtomobilId).orElseThrow(()->new InvalidAvtomobilIdxception(avtomobilId));

        Naracka naracka = new Naracka(datum,iznos,status,platena,covek1,kupuvac1,avtomobil);
        return this.narackaRepository.save(naracka);
    }

    @Override
    public Naracka plati(Long id) {
        Naracka naracka = this.narackaRepository.findById(id).orElseThrow(()->new InvalidNarackaIdException(id));
        naracka.setPlatena(true);
        return this.narackaRepository.save(naracka);
    }

    @Override
    public Naracka update(Long id) {
        Naracka naracka = this.narackaRepository.findById(id).orElseThrow(()->new InvalidNarackaIdException(id));
        naracka.setStatus(true);
        return this.narackaRepository.save(naracka);
    }

    @Override
    public List<Naracka> listAll() {
        return this.narackaRepository.findAll();
    }

    @Override
    public Naracka findById(Long id) {
        return this.narackaRepository.findById(id).orElseThrow(()->new InvalidNarackaIdException(id));
    }

    @Override
    public void delete(Long id) {
        Naracka naracka = this.narackaRepository.findById(id).orElseThrow(()->new InvalidNarackaIdException(id));
        this.narackaRepository.delete(naracka);
    }

    @Override
    public List<Naracka> listByIdNaSopstvenikAndStatus(Long id,boolean status) {
        Covek sopstvenik = this.covekRepository.findById(id).orElseThrow(()->new InvalidCovekImeException(id));
        List<Naracka> narackaList  = this.narackaRepository.findAllByCovekAndStatus(sopstvenik,status);
        return narackaList;
    }

    @Override
    public List<Naracka> listByIdNaKupuvacAndStatus(Long id, boolean status) {
        Covek kupuvac = this.covekRepository.findById(id).orElseThrow(()->new InvalidCovekImeException(id));
        List<Naracka> narackaList  = this.narackaRepository.findAllByKupuvacAndStatus(kupuvac,status);
        return narackaList;
    }
}
