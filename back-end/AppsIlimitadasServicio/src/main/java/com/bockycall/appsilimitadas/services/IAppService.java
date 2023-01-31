package com.bockycall.appsilimitadas.services;

import com.bockycall.appsilimitadas.entities.App;

public interface IAppService {
    Iterable<App> listarApps();
    App guardarApp(App app);
    App buscarPorName(String name);
    void eliminarApp(App app);
}
