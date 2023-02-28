package com.ecommerce.domain.prices.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Contiene informacion base para realizar la consulta
 */
@Data
@Builder
public class PriceRequest {

    private LocalDateTime appDate;
    private Integer productId;
    private Integer brandId;

}
