package it.nilaksha.cartservice.service;

import it.nilaksha.cartservice.config.MQConfiguration;
import it.nilaksha.cartservice.mapper.CartMapper;
import it.nilaksha.cartservice.model.Cart;
import it.nilaksha.cartservice.model.CartEntity;
import it.nilaksha.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartConsumerImpl implements CartConsumer {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    @Override
    @JmsListener(destination = MQConfiguration.CART_QUEUE)
    public void receiveMessage(@Payload Cart cart) {

        log.info("receiveMessage() -> Received Cart <" + cart + ">");

        CartEntity savedEntity = cartRepository.save(cartMapper.dtoToEntity(cart));

        log.info("receiveMessage() -> Cart added [id:" + savedEntity.getId() + "]");

    }
}
