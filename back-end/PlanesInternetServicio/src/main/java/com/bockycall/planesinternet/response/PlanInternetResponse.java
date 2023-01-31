package com.bockycall.planesinternet.response;

import com.bockycall.planesinternet.request.InfoExtraRequest;
import lombok.Data;

import java.util.List;

@Data
public class PlanInternetResponse {
    private Long id;
    private String title;
    private String subtitle;
    private float price;
    private int duration;

    private List<InfoExtraRequest> infoExtras;
}
