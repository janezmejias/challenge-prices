package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.mapper.RequestMapper;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import com.ecommerce.infrastructure.adapters.out.jpa.entity.Brand;
import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import com.ecommerce.infrastructure.adapters.out.jpa.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DomainPriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @Mock
    private RequestMapper requestMapper;

    @InjectMocks
    private DomainPriceService domainPriceService;

    private final List<Price> prices = new ArrayList<>();
    private final PriceResponse priceResponse = new PriceResponse();
    private final LocalDateTime appDate = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("ZARA");

        for (int i = 0; i < 2; i++) {
            int valor = i + 1;
            Price price = new Price();
            price.setId(valor);
            price.setPriority(valor);
            price.setPriceList(new BigDecimal(valor));
            price.setFinalPrice(new BigDecimal(20 * valor));
            price.setProductId(35455);
            price.setBrand(brand);
            prices.add(price);
        }

        priceResponse.setBrandId(1);
        priceResponse.setAppDate(appDate);
        priceResponse.setPrice(BigDecimal.valueOf(2));
        priceResponse.setFinalPrice(BigDecimal.valueOf(40));
        priceResponse.setProductId(35455);
    }

    @Test
    void testGetPrice() {
        PriceRequest priceRequest = PriceRequest.builder().appDate(appDate).brandId(1).productId(35455).build();

        given(priceRepository.findByBrandProductAndDate(1, 35455, appDate)).willReturn(prices);
        given(requestMapper.of(priceRequest)).willReturn(priceResponse);

        domainPriceService.getPrice(priceRequest).ifPresent(priceResponse -> {
            assertEquals(1, priceResponse.getBrandId());
            assertEquals(new BigDecimal(2).setScale(2, RoundingMode.HALF_EVEN), priceResponse.getPrice().setScale(2, RoundingMode.HALF_EVEN));
            assertEquals(appDate, priceResponse.getAppDate());
            assertEquals(new BigDecimal(40).setScale(2, RoundingMode.HALF_EVEN), priceResponse.getFinalPrice().setScale(2, RoundingMode.HALF_EVEN));
            assertEquals(35455, priceResponse.getProductId());
        });
    }
}