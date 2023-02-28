package com.ecommerce.domain.prices;

import com.ecommerce.domain.prices.mapper.RequestMapper;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.request.PriceRequestContext;
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
import java.util.Objects;

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

    private List<Price> prices = new ArrayList<>();
    private PriceResponse priceResponse = new PriceResponse();
    private LocalDateTime appDate = LocalDateTime.now();
    private PriceRequestContext priceRequestContext;

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

        priceRequestContext = PriceRequestContext.builder()
                .priceRequest(priceRequest)
                .price(prices.get(1))
                .build();

        given(priceRepository.findByBrandProductAndDate(1, 35455, appDate)).willReturn(prices);
        given(requestMapper.of(priceRequestContext)).willReturn(priceResponse);

        PriceResponse localPriceResponse = domainPriceService.getPrice(priceRequest);
        if (Objects.nonNull(localPriceResponse)) {
            assertEquals(1, localPriceResponse.getBrandId());
            assertEquals(new BigDecimal(2).setScale(2, RoundingMode.HALF_EVEN), localPriceResponse.getPrice().setScale(2, RoundingMode.HALF_EVEN));
            assertEquals(appDate, localPriceResponse.getAppDate());
            assertEquals(new BigDecimal(40).setScale(2, RoundingMode.HALF_EVEN), localPriceResponse.getFinalPrice().setScale(2, RoundingMode.HALF_EVEN));
            assertEquals(35455, localPriceResponse.getProductId());
        }
    }
}