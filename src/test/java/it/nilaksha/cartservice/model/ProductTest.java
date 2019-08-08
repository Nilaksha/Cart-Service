package it.nilaksha.cartservice.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier
                .forClass(Product.class)
                .withIgnoredFields("id")
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void testToString() {

        Product product = new Product();
        product.setId(1L);
        product.setCode("PR01");
        product.setName("Voucher");
        product.setDescription("Voucher to be used with any purchase");

        assertEquals("Product(id=1, code=PR01, name=Voucher, description=Voucher to be used with any purchase)", product.toString());

    }

}