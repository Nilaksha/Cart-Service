package it.nilaksha.cartservice.service;

import it.nilaksha.cartservice.config.MQConfiguration;
import it.nilaksha.cartservice.model.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartPublisherImpl implements CartPublisher {

    private final JmsTemplate jmsTemplate;

    @Override
    public void publishCart(Cart cart) {
        log.info("publishCart() -> Sending Cart to queue <" + cart + ">");
        jmsTemplate.convertAndSend(MQConfiguration.CART_QUEUE, cart);
    }
}
