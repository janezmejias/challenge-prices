package com.ecommerce.domain.prices.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponse {

    private BigDecimal price;
    private Integer brandId;
    private Integer productId;
    private LocalDateTime appDate;
    private BigDecimal finalPrice;

}
