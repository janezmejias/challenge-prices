package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.mapper.RequestMapper;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import com.ecommerce.infrastructure.adapters.out.jpa.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;
    private final RequestMapper requestMapper;

    @Override
    public Optional<PriceResponse> getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate());

        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(Price::getPriority)).get();
            priceRequest.setPrice(price);
            PriceResponse priceResponse = requestMapper.of(priceRequest);

            return Optional.of(priceResponse);
        }

        return Optional.empty();
    }

}