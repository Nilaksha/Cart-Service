package it.nilaksha.cartservice.service;

import it.nilaksha.cartservice.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartPublisherTest {

    @Mock
    private JmsTemplate jmsTemplate;

    private CartPublisher cartPublisher;

    @BeforeEach
    void init() {
        cartPublisher = new CartPublisherImpl(jmsTemplate);
    }


    @Test
    void testPublishCart() {

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductIds(Collections.singleton(1L));
        cart.setExpirationIn(300L);

        doNothing().when(jmsTemplate).convertAndSend(anyString(),any(Cart.class));

        cartPublisher.publishCart(cart);

        verify(jmsTemplate, times(1)).convertAndSend(anyString(),any(Cart.class));

    }

}