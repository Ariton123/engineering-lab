package com.example.buyorrentacar.service.impl;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.exceptions.InvalidAvtomobilIdxception;
import com.example.buyorrentacar.model.exceptions.InvalidCovekIdException;
import com.example.buyorrentacar.model.exceptions.UserNotFoundException;
import com.example.buyorrentacar.repository.AvtomobilRepository;
import com.example.buyorrentacar.repository.CovekRepository;
import com.example.buyorrentacar.service.AvtomobilService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvtomobilServiceImpl implements AvtomobilService {

    private final AvtomobilRepository avtomobilRepository;
    private final CovekRepository covekRepository;

    public AvtomobilServiceImpl(AvtomobilRepository avtomobilRepository, CovekRepository covekRepository) {
        this.avtomobilRepository = avtomobilRepository;
        this.covekRepository = covekRepository;
    }

    @Override
    public List<Avtomobil> listAll() {
        return this.avtomobilRepository.findAll();
    }

    @Override
    public Optional<Avtomobil> findById(Long id) {
        return this.avtomobilRepository.findById(id);
    }

    @Override
    public Avtomobil create(String marka, String model, String menuvac, String boja, Integer kilometraza, Integer godinaProizvodstvo, String tipGorivo, String kategorija, Integer cena, Long covekId) {
        Covek covek = this.covekRepository.findById(covekId).orElseThrow(()->new InvalidCovekIdException(covekId));
        Avtomobil avtomobil = new Avtomobil(marka,model,menuvac,boja,kilometraza,godinaProizvodstvo,tipGorivo,
                kategorija,cena,covek);
        return this.avtomobilRepository.save(avtomobil);
    }

    @Override
    public Avtomobil update(Long id,String marka, String model, String menuvac, String boja, Integer kilometraza, Integer godinaProizvodstvo, String tipGorivo, String kategorija, Integer cena) {
        Avtomobil avtomobil = this.avtomobilRepository.findById(id).orElseThrow(()->new InvalidAvtomobilIdxception(id));
        avtomobil.setMarka(marka);
        avtomobil.setModel(model);
        avtomobil.setMenuvac(menuvac);
        avtomobil.setBoja(boja);
        avtomobil.setKilometraza(kilometraza);
        avtomobil.setGodinaProizvodstvo(godinaProizvodstvo);
        avtomobil.setTipGorivo(tipGorivo);
        avtomobil.setKategorija(kategorija);
        avtomobil.setCena(cena);
        return this.avtomobilRepository.save(avtomobil);

    }

    @Override
    public void  deleteById(Long id) {
        this.avtomobilRepository.deleteById(id);
    }

    @Override
    public List<Avtomobil> listByUserId(Long id) {

        Covek covek1 = this.covekRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        List<Avtomobil> avtomobils = this.avtomobilRepository.findAllByCovek(covek1);
        return avtomobils;
    }
}
