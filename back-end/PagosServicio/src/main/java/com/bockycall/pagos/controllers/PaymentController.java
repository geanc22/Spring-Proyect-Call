package com.bockycall.pagos.controllers;

import com.bockycall.pagos.entities.Payment;
import com.bockycall.pagos.servicies.IPaymentService;
import com.bockycall.pagos.utilities.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @GetMapping
    public ResponseEntity listarPagos(){
        List<Payment> listaResponse = paymentService.listarPagos();
        if(listaResponse.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(listaResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPago(@PathVariable Long id){

        Payment paymentResponse = paymentService.buscarPago(id);
        Map<String,Object> response = new HashMap<>();
        if(paymentResponse == null){
            response.put("mensaje","no se pudo encontrar el pago");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarPago(@Valid @RequestBody PaymentRequest paymentRequest, BindingResult result){
        Payment payment;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            payment = paymentService.guardarPago(paymentRequest);
            if(payment == null){
                response.put("mensaje", "error al guardar el pago");
                response.put("error","no hay datos suficientes");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "error al registrar el pago");
            response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "pago realizado con éxito");
        response.put("payment", payment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
