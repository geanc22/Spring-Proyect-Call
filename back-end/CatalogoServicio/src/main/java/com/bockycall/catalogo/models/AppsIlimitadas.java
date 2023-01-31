package com.bockycall.catalogo.models;

import lombok.Data;

import java.util.List;

@Data
public class AppsIlimitadas {
    private String id;
    private String title;
    private float price;
    private String img;
    private int duration;
    private List<App> apps;
}
