package com.bockycall.pagos.utilities;

import com.bockycall.pagos.entities.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private String idProducto;
    private String typeProduct;
    private Float amount;
    private Client client;
    private Boolean terms;
    private String createAt;
}
