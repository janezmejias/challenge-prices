package com.ecommerce.domain.prices.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponse {

    private Integer productId;
    private Integer brandId;
    private BigDecimal price;
    private LocalDateTime appDate;
    private BigDecimal finalPrice;

}
