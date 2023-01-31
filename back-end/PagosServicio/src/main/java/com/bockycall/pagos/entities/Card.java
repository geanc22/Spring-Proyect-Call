package com.bockycall.pagos.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotNull
    @Pattern(regexp = "^5[1-5][0-9]{14}$")
    private String number;
    @Valid
    @NotNull
    @Column(name = "exp_month")
    private String expMonth;
    @Valid
    @NotNull
    @Column(name = "exp_year")
    private String expYear;
    @Valid
    @NotNull
    @Pattern(regexp = "^[0-9]{3,4}$")
    private String cvv;

    public Card(Card req){
        this.number = req.getNumber();
        this.expMonth = req.getExpMonth();
        this.expYear = req.getExpYear();
        this.cvv = req.getCvv();
    }
}
