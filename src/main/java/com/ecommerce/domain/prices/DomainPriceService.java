package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.exceptions.BrandProductAndDateNotFound;
import com.ecommerce.domain.prices.mapper.RequestMapper;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.request.PriceRequestContext;
import com.ecommerce.domain.prices.response.PriceResponse;
import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import com.ecommerce.infrastructure.adapters.out.jpa.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;
    private final RequestMapper requestMapper;

    /**
     * El metodo getPrice tiene como responsabilidad filtrar mediante los paramtros
     * brandId / productId / appDate la informacion localizada en la db de memoria
     *
     * @param priceRequest Contiene informacion base para realizar la consulta
     * @return Optional<PriceResponse>
     */
    @Override
    public PriceResponse getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandProductAndDate(
                priceRequest.getBrandId(),
                priceRequest.getProductId(),
                priceRequest.getAppDate());

        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(Price::getPriority)).get();
            return requestMapper.of(PriceRequestContext.builder()
                    .priceRequest(priceRequest)
                    .price(price)
                    .build());
        }

        throw new BrandProductAndDateNotFound();
    }

}
