package it.nilaksha.cartservice.service;

import it.nilaksha.cartservice.model.Cart;

public interface CartConsumer {

    void receiveMessage(Cart cart);

}
