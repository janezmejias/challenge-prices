package com.ecommerce.domain.prices.request;

import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceRequest {

    private Integer brandId;
    private Integer productId;
    private LocalDateTime appDate;
    private Price price;

}
