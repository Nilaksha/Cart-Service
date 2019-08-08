package it.nilaksha.cartservice.repository;

import it.nilaksha.cartservice.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p from ProductEntity p where p.id in :productIds")
    List<ProductEntity> findAllByProductId(@Param("productIds") Iterable<Long> productIds);

}
