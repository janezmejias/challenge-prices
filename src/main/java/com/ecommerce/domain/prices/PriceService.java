package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;

import java.util.Optional;

public interface PriceService {

    Optional<PriceResponse> getPrice(PriceRequest priceRequest);
}
