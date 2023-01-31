package com.bockycall.planesinternet.services;

import com.bockycall.planesinternet.entities.InfoExtra;
import com.bockycall.planesinternet.entities.PlanInternet;
import com.bockycall.planesinternet.repositories.IInfoExtraRepository;
import com.bockycall.planesinternet.repositories.IPlanInternetRepository;
import com.bockycall.planesinternet.request.InfoExtraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfoExtraServiceImpl implements InfoExtraService {
    @Autowired
    private IInfoExtraRepository dao;
    @Autowired
    private IPlanInternetRepository planDao;

    @Override
    public Iterable<InfoExtra> listarInfoExtras() {
        return dao.findAll();
    }

    @Override
    public InfoExtra guardarInfoExtra(InfoExtra infoExtra) {
        return dao.save(infoExtra);
    }

    @Override
    @Transactional
    public void actualizarInfoExtra(List<InfoExtraRequest> infoExtras,Long idPlan) {
        PlanInternet planInternet = planDao.findById(idPlan).orElse(null);
        this.eliminarInfoExtraDelPlan(idPlan);
        for(InfoExtraRequest info: infoExtras){
            InfoExtra infoExtra = new InfoExtra();
            infoExtra.setProperty(info.getProperty());
            infoExtra.setValue(info.getValue());
            infoExtra.setPlanInternet(planInternet);
            this.guardarInfoExtra(infoExtra);
        }
    }

    @Override
    public List<InfoExtra> buscarInfoExtraPorPlan(Long id) {
        return dao.findByPlanInternetId(id);
    }

    @Override
    public InfoExtra buscarInfoExtra(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void eliminarInfoExtra(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void eliminarInfoExtraDelPlan(Long id) {
        dao.deleteByPlanInternetId(id);
    }
}
