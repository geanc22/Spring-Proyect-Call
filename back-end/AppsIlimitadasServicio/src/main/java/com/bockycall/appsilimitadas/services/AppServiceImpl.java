package com.bockycall.appsilimitadas.services;

import com.bockycall.appsilimitadas.entities.App;
import com.bockycall.appsilimitadas.repositories.IAppsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements IAppService {
    @Autowired
    private IAppsRepository repository;

    @Override
    public Iterable<App> listarApps() {
        return repository.findAll();
    }

    @Override
    public App guardarApp(App app) {
        return repository.save(app);
    }

    @Override
    public App buscarPorName(String name) {
        return repository.findAppByName(name);
    }

    @Override
    public void eliminarApp(App app) {
        repository.delete(app);
    }
}
