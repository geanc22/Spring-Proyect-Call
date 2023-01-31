package com.bockycall.pagos.servicies;

import com.bockycall.pagos.entities.Payment;
import com.bockycall.pagos.utilities.PaymentRequest;

import java.util.List;

public interface IPaymentService {
    List<Payment> listarPagos();
    Payment guardarPago(PaymentRequest PaymentRequest);
    Payment buscarPago(Long id);

}
