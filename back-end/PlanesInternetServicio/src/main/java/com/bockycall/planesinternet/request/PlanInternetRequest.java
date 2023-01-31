package com.bockycall.planesinternet.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanInternetRequest {
    private Long id;
    private String title;
    private String subtitle;
    private float price;
    private int duration;
    private List<InfoExtraRequest> infoExtras;

}
