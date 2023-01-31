package com.bockycall.appsilimitadas.services;

import com.bockycall.appsilimitadas.entities.AppsIlimitadas;
import com.bockycall.appsilimitadas.repositories.IAppsIlimitadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppsIlimitadasServiceImpl implements IAppsIlimitadasService {

    @Autowired
    private IAppsIlimitadasRepository dao;

    @Override
    public Iterable<AppsIlimitadas> listarAppsIlimitadas() {
        return dao.findAll();
    }

    @Override
    public AppsIlimitadas guardarAppsIlimitadas(AppsIlimitadas appsIlimitadas) {
        return dao.save(appsIlimitadas);
    }

    @Override
    public AppsIlimitadas buscarAppsIlimitadas(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void eliminarAppsIlimitadas(String id) {
        dao.deleteById(id);
    }
}
