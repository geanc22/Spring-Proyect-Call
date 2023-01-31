package com.bockycall.recargas.services;

import com.bockycall.recargas.entities.Recarga;
import com.bockycall.recargas.repositories.IRecargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecargaServiceImpl implements IRecargaService{

    @Autowired
    private IRecargaRepository dao;
    @Override
    public Iterable<Recarga> listarRecargas() {
        return dao.findAll();
    }

    @Override
    public Recarga guardarRecarga(Recarga recarga) {
        return dao.save(recarga);
    }

    @Override
    public Recarga buscarRecarga(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void eliminarRecarga(Recarga recarga) {
        dao.delete(recarga);
    }
}
