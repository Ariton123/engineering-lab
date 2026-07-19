package com.example.buyorrentacar.service.impl;

import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.exceptions.InvalidArgumentsException;
import com.example.buyorrentacar.model.exceptions.InvalidUserCredentialsException;
import com.example.buyorrentacar.repository.CovekRepository;
import com.example.buyorrentacar.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final CovekRepository covekRepository;

    public AuthServiceImpl(CovekRepository covekRepository) {
        this.covekRepository = covekRepository;
    }

    @Override
    public Covek login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        return covekRepository.findByImeAndPasswordQuery(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
