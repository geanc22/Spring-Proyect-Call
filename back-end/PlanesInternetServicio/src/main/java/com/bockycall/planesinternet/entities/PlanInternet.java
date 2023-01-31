package com.bockycall.planesinternet.entities;

import com.bockycall.planesinternet.request.PlanInternetRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "planes_internet")
public class PlanInternet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private float price;
    private int duration;

    @OneToMany(mappedBy = "planInternet",cascade = CascadeType.ALL)
    private List<InfoExtra> infoExtras;

    public PlanInternet(PlanInternetRequest request){
        this.title = request.getTitle();
        this.subtitle = request.getSubtitle();
        this.price = request.getPrice();
        this.duration = request.getDuration();
    }

}
