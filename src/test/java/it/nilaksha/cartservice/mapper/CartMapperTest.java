package it.nilaksha.cartservice.mapper;

import it.nilaksha.cartservice.model.Cart;
import it.nilaksha.cartservice.model.CartEntity;
import it.nilaksha.cartservice.model.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartMapperTest {

    @Mock
    private CartMapperResolver cartMapperResolver;

    @InjectMocks
    private CartMapperImpl cartMapper;

    @Test
    public void testEntityToDto() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(10L);
        productEntity.setCode("PR01");
        productEntity.setName("Voucher");
        productEntity.setDescription("Voucher to be used with any purchase");

        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(1L);
        cartEntity.setExpirationIn(300L);
        cartEntity.setProductEntities(productEntities);

        when(cartMapperResolver.findProductIdsByEntity(anyList())).thenReturn(Collections.singletonList(10L));

        Cart cart = cartMapper.entityToDto(cartEntity);

        assertNotNull(cart);
        assertEquals(1L, cart.getId().longValue());
        assertEquals(300L, cart.getExpirationIn().longValue());
        assertEquals(Collections.singletonList(10L), cart.getProductIds());

    }

    @Test
    public void testDtoToEntity() {

        List<Long> productIds = new ArrayList<>();
        productIds.add(10L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setProductIds(productIds);
        cart.setExpirationIn(300L);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(10L);
        productEntity.setCode("PR01");
        productEntity.setName("Voucher");
        productEntity.setDescription("Voucher to be used with any purchase");

        List<ProductEntity> productEntities =  new ArrayList<>();
        productEntities.add(productEntity);

        when(cartMapperResolver.findProductsById(anyList())).thenReturn(productEntities);

        CartEntity cartEntity = cartMapper.dtoToEntity(cart);

        assertNotNull(cartEntity);
        assertEquals(300L, cartEntity.getExpirationIn().longValue());
        assertEquals(10L, ((List<ProductEntity>) cartEntity.getProductEntities()).get(0).getId().longValue());
        assertEquals("PR01", ((List<ProductEntity>) cartEntity.getProductEntities()).get(0).getCode());
        assertEquals("Voucher", ((List<ProductEntity>) cartEntity.getProductEntities()).get(0).getName());
        assertEquals("Voucher to be used with any purchase", ((List<ProductEntity>) cartEntity.getProductEntities()).get(0).getDescription());

    }

}