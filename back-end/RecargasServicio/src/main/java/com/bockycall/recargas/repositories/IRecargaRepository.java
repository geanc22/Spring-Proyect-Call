package com.bockycall.recargas.repositories;

import com.bockycall.recargas.entities.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecargaRepository extends JpaRepository<Recarga,Long> {
}
