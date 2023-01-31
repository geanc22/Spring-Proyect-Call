package com.bockycall.pagos.servicies.impls;

import com.bockycall.pagos.entities.Card;
import com.bockycall.pagos.entities.Client;
import com.bockycall.pagos.entities.Payment;
import com.bockycall.pagos.repositories.IPaymentRepository;
import com.bockycall.pagos.servicies.ICardService;
import com.bockycall.pagos.servicies.IClientService;
import com.bockycall.pagos.servicies.IPaymentService;
import com.bockycall.pagos.utilities.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentDao;
    @Autowired
    private IClientService clientService;
    @Autowired
    private ICardService cardService;

    @Override
    public List<Payment> listarPagos() {
        return paymentDao.findAll();
    }

    @Override
    @Transactional
    public Payment guardarPago(PaymentRequest paymentRequest) {
        if(paymentRequest.getClient() == null || paymentRequest.getClient().getCard() == null){
            return null;
        }
        Card card = cardService.guardarCard(paymentRequest.getClient().getCard());
        Client client = clientService.guardarCliente(paymentRequest.getClient(), card);

        Payment payment = new Payment();
        payment.setIdProducto(paymentRequest.getIdProducto());
        payment.setTypeProduct(paymentRequest.getTypeProduct());
        payment.setAmount(paymentRequest.getAmount());
        payment.setClient(client);
        payment.setTerms(paymentRequest.getTerms());
        payment = paymentDao.save(payment);
        return payment;
    }

    @Override
    public Payment buscarPago(Long id) {
        Payment payment = paymentDao.findById(id).orElse(null);
        return payment;
    }
}
