package com.bockycall.appsilimitadas.services;

import com.bockycall.appsilimitadas.entities.AppsIlimitadas;

public interface IAppsIlimitadasService {
    Iterable<AppsIlimitadas> listarAppsIlimitadas();

    AppsIlimitadas guardarAppsIlimitadas(AppsIlimitadas appsIlimitadas);

    AppsIlimitadas buscarAppsIlimitadas(String id);

    void eliminarAppsIlimitadas(String id);
}
