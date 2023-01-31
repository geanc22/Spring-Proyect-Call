package com.bockycall.planesinternet.services;

import com.bockycall.planesinternet.entities.InfoExtra;
import com.bockycall.planesinternet.request.InfoExtraRequest;

import java.util.List;

public interface InfoExtraService {
    Iterable<InfoExtra> listarInfoExtras();

    InfoExtra guardarInfoExtra(InfoExtra infoExtra);
    void actualizarInfoExtra(List<InfoExtraRequest> infoExtras,Long idPlan);

    List<InfoExtra> buscarInfoExtraPorPlan(Long id);
    InfoExtra buscarInfoExtra(Long id);

    void eliminarInfoExtra(Long id);
    void eliminarInfoExtraDelPlan(Long id);
}
