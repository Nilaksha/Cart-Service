package it.nilaksha.cartservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Product {

    @EqualsAndHashCode.Exclude
    private Long id;

    private String code;

    private String name;

    private String description;

}
