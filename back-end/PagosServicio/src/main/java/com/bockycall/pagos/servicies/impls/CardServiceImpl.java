package com.bockycall.pagos.servicies.impls;

import com.bockycall.pagos.entities.Card;
import com.bockycall.pagos.repositories.ICardRepository;
import com.bockycall.pagos.servicies.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private ICardRepository dao;

    @Override
    @Transactional
    public Card guardarCard(Card card) {
        Card cardNuevo = new Card();
        cardNuevo.setNumber(card.getNumber());
        cardNuevo.setExpMonth(card.getExpMonth());
        cardNuevo.setExpYear(card.getExpYear());
        cardNuevo.setCvv(card.getCvv());
        return dao.save(cardNuevo);
    }
}
