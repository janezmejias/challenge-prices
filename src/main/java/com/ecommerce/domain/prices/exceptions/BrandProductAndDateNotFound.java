package com.ecommerce.domain.prices.exceptions;

public class BrandProductAndDateNotFound extends RuntimeException {

    public BrandProductAndDateNotFound() {
        super("La combinacion de [brand, product_id y fecha de la aplicacion] son invalidas");
    }

}
