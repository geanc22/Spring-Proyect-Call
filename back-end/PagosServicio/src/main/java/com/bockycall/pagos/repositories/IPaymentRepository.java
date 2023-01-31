package com.bockycall.pagos.repositories;

import com.bockycall.pagos.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment,Long> {

}
