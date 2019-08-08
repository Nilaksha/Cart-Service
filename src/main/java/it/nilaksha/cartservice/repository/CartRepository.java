package it.nilaksha.cartservice.repository;

import it.nilaksha.cartservice.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
