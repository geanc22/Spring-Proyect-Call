package com.bockycall.recargas.services;

import com.bockycall.recargas.entities.Recarga;

public interface IRecargaService {
    Iterable<Recarga> listarRecargas();
    Recarga guardarRecarga(Recarga recarga);
    Recarga buscarRecarga(Long id);
    void eliminarRecarga(Recarga recarga);
}
