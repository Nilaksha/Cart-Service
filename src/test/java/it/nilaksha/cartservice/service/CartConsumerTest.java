package it.nilaksha.cartservice.service;

import it.nilaksha.cartservice.mapper.CartMapper;
import it.nilaksha.cartservice.model.Cart;
import it.nilaksha.cartservice.model.CartEntity;
import it.nilaksha.cartservice.model.ProductEntity;
import it.nilaksha.cartservice.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartConsumerTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    private CartConsumer cartConsumer;

    @BeforeEach
    void init() {
        cartConsumer = new CartConsumerImpl(cartRepository, cartMapper);
    }

    @Test
    void testReceiveMessage() {

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductIds(Collections.singleton(1L));
        cart.setExpirationIn(300L);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setCode("PR01");
        productEntity.setName("Voucher");
        productEntity.setDescription("Voucher to be used with any purchase");

        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(1L);
        cartEntity.setProductEntities(Collections.singleton(productEntity));
        cartEntity.setExpirationIn(300L);

        when(cartMapper.dtoToEntity(any(Cart.class))).thenReturn(cartEntity);
        when(cartRepository.save(any(CartEntity.class))).thenReturn(cartEntity);

        cartConsumer.receiveMessage(cart);

        verify(cartMapper, times(1)).dtoToEntity(any(Cart.class));
        verify(cartRepository, times(1)).save(any(CartEntity.class));

    }

}