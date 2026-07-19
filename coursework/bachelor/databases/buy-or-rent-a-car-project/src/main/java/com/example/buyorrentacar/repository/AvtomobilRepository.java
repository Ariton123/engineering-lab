package com.example.buyorrentacar.repository;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvtomobilRepository extends JpaRepository<Avtomobil,Long> {
    @Query( "SELECT a FROM Avtomobil a WHERE a.id = :id")
    Optional<Avtomobil> findById(Long id);
    @Query( "select a from Avtomobil a where a.covek = :covek")
    List<Avtomobil> findAllByCovek(Covek covek);
}
