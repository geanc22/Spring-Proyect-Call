package com.bockycall.planesinternet.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity()
@Table(name = "info_extras")
public class InfoExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String property;
    private String value;

    @ManyToOne
    @JoinColumn(name = "planInternet_id")
    private PlanInternet planInternet;

}
