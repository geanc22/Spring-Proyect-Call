package com.bockycall.pagos.servicies;

import com.bockycall.pagos.entities.Card;
import com.bockycall.pagos.entities.Client;

public interface IClientService {

    Client guardarCliente(Client client, Card card);
}
