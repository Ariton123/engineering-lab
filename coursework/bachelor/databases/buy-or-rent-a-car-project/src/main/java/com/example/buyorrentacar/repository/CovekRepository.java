package com.example.buyorrentacar.repository;

import com.example.buyorrentacar.model.Covek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CovekRepository extends JpaRepository<Covek,Long> {

    @Query("select c from Covek c where c.ime= :name and c.password = :password")
    Optional<Covek> findByImeAndPasswordQuery(String name, String password);
    @Query("select  c from Covek c where LOWER(c.email) = LOWER(:email)")
    Optional<Covek> findByEmail(String email);
    @Query("select c from Covek c where c.id = :id")
    Optional<Covek> findById(Long id);
    @Query("select c from Covek c where LOWER(c.ime) = LOWER(:name)")
    Optional<Covek> findByIme(String name);
}
