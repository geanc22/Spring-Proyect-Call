package com.bockycall.planesinternet.services;

import com.bockycall.planesinternet.entities.InfoExtra;
import com.bockycall.planesinternet.entities.PlanInternet;
import com.bockycall.planesinternet.repositories.IPlanInternetRepository;
import com.bockycall.planesinternet.request.InfoExtraRequest;
import com.bockycall.planesinternet.response.PlanInternetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanInternetServiceImpl implements PlanInternetService {

    @Autowired
    private IPlanInternetRepository dao;

    @Autowired
    private InfoExtraService infoService;
    @Override
    public Iterable<PlanInternetResponse> listarPlanInternet() {
        List<PlanInternet> list = dao.findAll();
        List<PlanInternetResponse> responseList = new ArrayList<>();
        list.forEach(planInternet -> {
            PlanInternetResponse pResponse = new PlanInternetResponse();
            pResponse.setId(planInternet.getId());
            pResponse.setTitle(planInternet.getTitle());
            pResponse.setSubtitle(planInternet.getSubtitle());
            pResponse.setPrice(planInternet.getPrice());
            pResponse.setDuration(planInternet.getDuration());
            List<InfoExtraRequest> infoExtras = new ArrayList<>();
            for (InfoExtra info : planInternet.getInfoExtras()) {
                InfoExtraRequest infoExtraRequest = new InfoExtraRequest();
                infoExtraRequest.setProperty(info.getProperty());
                infoExtraRequest.setValue(info.getValue());
                infoExtras.add(infoExtraRequest);
            }
            pResponse.setInfoExtras(infoExtras);
            responseList.add(pResponse);
        });
        return responseList;
    }

    @Override
    public PlanInternet guardar(PlanInternet planInternet) {
        return dao.save(planInternet);
    }

    @Override
    public PlanInternet buscar(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public PlanInternetResponse obtenerPlanInternetResponse(Long id) {
        PlanInternetResponse planResponse = new PlanInternetResponse();
        PlanInternet planInternet = this.buscar(id);
        if (planInternet == null) {
            return null;
        }
        planResponse.setId(planInternet.getId());
        planResponse.setTitle(planInternet.getTitle());
        planResponse.setSubtitle(planInternet.getSubtitle());
        planResponse.setPrice(planInternet.getPrice());
        planResponse.setDuration(planInternet.getDuration());
        List<InfoExtraRequest> infoExtras = new ArrayList<>();
        for (InfoExtra info : infoService.buscarInfoExtraPorPlan(planInternet.getId())) {
            InfoExtraRequest infoExtraRequest = new InfoExtraRequest();
            infoExtraRequest.setProperty(info.getProperty());
            infoExtraRequest.setValue(info.getValue());
            infoExtras.add(infoExtraRequest);
        }
        planResponse.setInfoExtras(infoExtras);
        return planResponse;
    }

    @Override
    public void eliminar(Long id) {
        dao.deleteById(id);
    }
}
