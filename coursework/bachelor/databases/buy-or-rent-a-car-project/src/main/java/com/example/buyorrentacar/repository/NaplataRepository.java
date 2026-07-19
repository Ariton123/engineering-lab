package com.example.buyorrentacar.repository;

import com.example.buyorrentacar.model.Naplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaplataRepository extends JpaRepository<Naplata,Long> {
}
