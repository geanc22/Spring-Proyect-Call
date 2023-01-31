package com.bockycall.planesinternet.services;

import com.bockycall.planesinternet.entities.PlanInternet;
import com.bockycall.planesinternet.response.PlanInternetResponse;

public interface PlanInternetService {
    Iterable<PlanInternetResponse> listarPlanInternet();
    PlanInternet guardar(PlanInternet planInternet);
    PlanInternet buscar(Long id);
    PlanInternetResponse obtenerPlanInternetResponse(Long id);
    void eliminar(Long id);
}
