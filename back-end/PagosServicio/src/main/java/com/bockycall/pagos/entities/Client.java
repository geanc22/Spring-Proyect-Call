package com.bockycall.pagos.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    @Valid
    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "last_name_1")
    private String lastName1;
    @Valid
    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "last_name_2")
    private String lastName2;
    @Valid
    @NotNull
    @Pattern(regexp="^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")
    private String email;
    @Valid
    @NotNull
    @Pattern(regexp="^(\\d{3}[- ]?){2}\\d{3}$")
    private String telf;
    @Valid
    @NotNull
    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Client(Client req){
        this.name = req.getName();
        this.lastName1 = req.getLastName1();
        this.lastName2 = req.getLastName2();
        this.email = req.getEmail();
        this.telf = req.getTelf();
        this.card = req.getCard();
    }
}
