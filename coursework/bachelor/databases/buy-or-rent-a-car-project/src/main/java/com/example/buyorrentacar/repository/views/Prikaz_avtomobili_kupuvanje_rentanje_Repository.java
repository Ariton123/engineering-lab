package com.example.buyorrentacar.repository.views;

import com.example.buyorrentacar.model.views.prikaz_avtomobili_kupuvanje_rentanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Prikaz_avtomobili_kupuvanje_rentanje_Repository extends JpaRepository<prikaz_avtomobili_kupuvanje_rentanje,Long> {

}
