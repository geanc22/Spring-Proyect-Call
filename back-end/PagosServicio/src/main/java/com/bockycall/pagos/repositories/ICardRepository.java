package com.bockycall.pagos.repositories;

import com.bockycall.pagos.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<Card,Long> {
}
