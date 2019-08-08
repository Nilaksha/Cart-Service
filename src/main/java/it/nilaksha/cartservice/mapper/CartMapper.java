package it.nilaksha.cartservice.mapper;

import it.nilaksha.cartservice.model.Cart;
import it.nilaksha.cartservice.model.CartEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartMapperResolver.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    @Mapping(source = "productEntities", target = "productIds")
    Cart entityToDto(CartEntity cartEntity);

    @Mapping(source = "productIds", target = "productEntities")
    @Mapping(target = "id", ignore = true)
    CartEntity dtoToEntity(Cart cart);

}
