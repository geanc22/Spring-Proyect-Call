package com.bockycall.pagos.repositories;

import com.bockycall.pagos.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client,Long> {
}
