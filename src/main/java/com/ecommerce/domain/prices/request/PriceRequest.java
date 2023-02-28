package com.ecommerce.domain.prices.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceRequest {

    private LocalDateTime appDate;
    private Integer productId;
    private Integer brandId;

}
