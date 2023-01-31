package com.bockycall.pagos.servicies.impls;

import com.bockycall.pagos.entities.Card;
import com.bockycall.pagos.entities.Client;
import com.bockycall.pagos.repositories.IClientRepository;
import com.bockycall.pagos.servicies.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private IClientRepository dao;

    @Override
    public Client guardarCliente(Client client, Card card) {
        Client clienteNuevo = new Client();
        clienteNuevo.setName(client.getName());
        clienteNuevo.setLastName1(client.getLastName1());
        clienteNuevo.setLastName2(client.getLastName2());
        clienteNuevo.setEmail(client.getEmail());
        clienteNuevo.setTelf(client.getTelf());
        clienteNuevo.setCard(card);
        return dao.save(clienteNuevo);
    }

}
