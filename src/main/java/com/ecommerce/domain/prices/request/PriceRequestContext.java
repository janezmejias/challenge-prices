package com.ecommerce.domain.prices.request;

import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceRequestContext {

    private PriceRequest priceRequest;
    private Price price;
}
