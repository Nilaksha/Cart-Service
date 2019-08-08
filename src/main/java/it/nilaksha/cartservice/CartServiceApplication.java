package it.nilaksha.cartservice;

import it.nilaksha.cartservice.model.Cart;
import it.nilaksha.cartservice.model.ProductEntity;
import it.nilaksha.cartservice.repository.ProductRepository;
import it.nilaksha.cartservice.service.CartPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class CartServiceApplication implements ApplicationRunner {

    private final CartPublisher cartPublisher;

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        log.info("Embedded ActiveMQ For Cart Service");

        // Products are added for Demo purposes
        addProduct("PR01", "Gift Card", "Gift Card to br gifted to your loved ones");
        addProduct("PR02", "Voucher", "Voucher to be used with any purchase");

        // Messages(Carts) are published for Demo purposes
        for (int i = 0; i < 5; i++) {
            Cart cart = new Cart();
            cart.setExpirationIn(1800L);
            cart.setProductIds(Arrays.asList(1L, 2L));
            cartPublisher.publishCart(cart);
        }

        log.info("Waiting til ActiveMQ JMS Messages are consumed");
        TimeUnit.SECONDS.sleep(10);
        System.exit(0);
    }

    private void addProduct(String code, String name, String description) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode(code);
        productEntity.setName(name);
        productEntity.setDescription(description);
        ProductEntity savedEntity = productRepository.save(productEntity);
        log.info("Product added [id:" + savedEntity.getId() + "]");

    }

}
