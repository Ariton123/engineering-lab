package com.example.buyorrentacar.service;

import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface CovekService {
    Covek register(String ime, String prezime, String telefBroj, String email,String password, String adresa, Role role);
    UserDetails loadUserByUsername(String s);
    Covek findById(Long id);
}
