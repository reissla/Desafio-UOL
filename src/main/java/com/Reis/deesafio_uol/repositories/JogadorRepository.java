package com.Reis.deesafio_uol.repositories;

import com.Reis.deesafio_uol.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface JogadorRepository extends JpaRepository<Jogador, String> {

    // Consulta personalizada para buscar jogador por codinome
    @Query("SELECT j FROM Jogador j WHERE j.codinome = :codinome")
    Jogador findByCodinome(@Param("codinome") String codinome);
}
