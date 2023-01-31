package com.bockycall.pagos.entities;

import com.bockycall.pagos.utilities.PaymentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotNull
    @Column(name = "id_producto")
    private String idProducto;
    @Valid
    @NotNull
    @Column(name = "type_product")
    private String typeProduct;
    @Valid
    @NotNull
    private Float amount;
    @Valid
    @NotNull
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Valid
    @NotNull
    @AssertTrue(message = "Debe aceptar los terminos")
    private Boolean terms;
    @Column(name = "create_at")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createAt;

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

    public Payment(PaymentRequest req){
        this.idProducto = req.getIdProducto();
        this.typeProduct = req.getTypeProduct();
        this.amount = req.getAmount();
        this.client = req.getClient();
        this.terms = req.getTerms();
    }
}
