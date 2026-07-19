package com.example.buyorrentacar.service.impl;

import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Role;
import com.example.buyorrentacar.model.exceptions.InvalidCovekIdException;
import com.example.buyorrentacar.model.exceptions.InvalidCovekImeException;
import com.example.buyorrentacar.model.exceptions.InvalidUserEmailException;
import com.example.buyorrentacar.model.exceptions.UserNotFoundException;
import com.example.buyorrentacar.repository.CovekRepository;
import com.example.buyorrentacar.service.CovekService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CovekServiceImpl implements CovekService {

    private final CovekRepository covekRepository;

    public CovekServiceImpl(CovekRepository covekRepository) {
        this.covekRepository = covekRepository;
    }

    @Override
    public Covek register(String ime, String prezime, String telefBroj, String email, String password, String adresa, Role role) {
        Covek covek = new Covek(ime,prezime,telefBroj,email,password, adresa,role);
            return this.covekRepository.save(covek);

    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return  this.covekRepository.findByEmail(s).orElseThrow(()->new InvalidUserEmailException(s));
    }

    @Override
    public Covek findById(Long id) {
        return this.covekRepository.findById(id).orElseThrow(()->new InvalidCovekIdException(id));
    }
}
