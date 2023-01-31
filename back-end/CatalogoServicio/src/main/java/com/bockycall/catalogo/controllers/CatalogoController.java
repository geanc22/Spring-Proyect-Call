package com.bockycall.catalogo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/catalogo/")
public class CatalogoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/apps-ilimitadas")
    public Object listarAppsIlimitadas() {
        String url = "http://apps-ilimitadas-servicio/apps-ilimitadas";
        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("/apps-ilimitadas/{id}")
    public Object buscarAppIlimitada(@PathVariable String id) {
        Object response;
        try {
            response = restTemplate.getForObject("http://apps-ilimitadas-servicio/apps-ilimitadas/" + id, Object.class);
        } catch (HttpClientErrorException error) {
            return error.getResponseBodyAs(HashMap.class);
        }
        return response;
    }
    @GetMapping("/apps-ilimitadas/uploads/img/{imgName:.+}")
    public byte[] downloadFile(@PathVariable String imgName) {
        byte[] imageBytes = restTemplate.getForObject("http://apps-ilimitadas-servicio/apps-ilimitadas/uploads/img/"+imgName, byte[].class);

        return imageBytes;
    }
    @GetMapping("/planes-internet")
    public Object listarPlanesInternet() {
        String url = "http://planes-internet-servicio/planes-internet";
        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("/planes-internet/{id}")
    public Object buscarPlanInterntet(@PathVariable Long id) {
        Object response;
        try {
            response = restTemplate.getForObject("http://planes-internet-servicio/planes-internet/" + id, Object.class);
        } catch (HttpClientErrorException error) {
            return error.getResponseBodyAs(HashMap.class);
        }
        return response;
    }
    @GetMapping("/recargas")
    public Object listarRecargas() {
        String url = "http://recargas-servicio/recargas";
        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("/recargas/{id}")
    public Object buscarRecarga(@PathVariable Long id) {
        Object response;
        try {
            response = restTemplate.getForObject("http://recargas-servicio/recargas/" + id, Object.class);
        } catch (HttpClientErrorException error) {
            return error.getResponseBodyAs(HashMap.class);
        }
        return response;
    }
}
