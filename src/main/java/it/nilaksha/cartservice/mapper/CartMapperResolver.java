package it.nilaksha.cartservice.mapper;

import it.nilaksha.cartservice.model.ProductEntity;
import it.nilaksha.cartservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapperResolver {

    private final ProductRepository productRepository;

    public Collection<ProductEntity> findProductsById(Collection<Long> branchIds) {
        return productRepository.findAllByProductId(branchIds);
    }

    public Collection<Long> findProductIdsByEntity(Collection<ProductEntity> productEntities) {
        return productEntities.stream().map(ProductEntity::getId).collect(Collectors.toList());
    }
}
