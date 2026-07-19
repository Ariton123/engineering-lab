package com.example.buyorrentacar.repository;

import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Naracka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NarackaRepository extends JpaRepository<Naracka,Long> {
    @Query("SELECT n from Naracka n where n.covek = :covek")
    List<Naracka> findAllByCovek(Covek covek);
    @Query("SELECT n FROM Naracka n where n.kupuvac = :covek and n.status = :status")
    List<Naracka> findAllByKupuvacAndStatus(Covek covek,boolean status);
    @Query("select n from Naracka n where n.covek = :covek and n.status = :status")
    List<Naracka> findAllByCovekAndStatus(Covek covek,boolean status);
}
