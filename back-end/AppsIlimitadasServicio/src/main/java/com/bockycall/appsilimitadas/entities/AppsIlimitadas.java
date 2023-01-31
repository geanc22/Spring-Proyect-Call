package com.bockycall.appsilimitadas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "apps_ilimitadas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppsIlimitadas {
    @Id
    private String id;
    private String title;
    private float price;
    private String img;
    private int duration;
    private List<App> apps;

}
