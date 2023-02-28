package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;

public interface PriceService {

    /**
     * El metodo getPrice tiene como responsabilidad filtrar mediante los paramtros
     * brandId / productId / appDate la informacion localizada en la db de memoria
     *
     * @param priceRequest Contiene informacion base para realizar la consulta
     * @return Optional<PriceResponse>
     */
    PriceResponse getPrice(PriceRequest priceRequest);
}
