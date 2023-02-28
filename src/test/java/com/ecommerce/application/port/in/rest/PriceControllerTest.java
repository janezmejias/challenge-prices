package com.ecommerce.application.port.in.rest;

import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceControllerTest {

    @Autowired
    private PriceController priceController;

    @ParameterizedTest
    @CsvFileSource(resources = "/prices.csv", numLinesToSkip = 1)
    void restIntegrationTest(ArgumentsAccessor argumentsAccessor) {
        LocalDateTime appDate = LocalDateTime.of(
                argumentsAccessor.getInteger(2),
                argumentsAccessor.getInteger(3),
                argumentsAccessor.getInteger(4),
                argumentsAccessor.getInteger(5),
                0
        );

        PriceRequest priceRequest = PriceRequest.builder()
                .brandId(argumentsAccessor.getInteger(0))
                .productId(argumentsAccessor.getInteger(1))
                .appDate(appDate).build();

        ResponseEntity<PriceResponse> priceResponse = priceController.getPrice(priceRequest);
        if (priceResponse.getStatusCode() == HttpStatus.OK) {
            PriceResponse price = priceResponse.getBody();
            if (Objects.nonNull(price)) {
                assertEquals(price.getBrandId(), argumentsAccessor.getInteger(0));
                assertEquals(price.getProductId(), argumentsAccessor.getInteger(1));
                assertEquals(price.getAppDate(), appDate);
                assertEquals(price.getFinalPrice().setScale(2, RoundingMode.HALF_EVEN), BigDecimal.valueOf(argumentsAccessor.getDouble(6)).setScale(2, RoundingMode.HALF_EVEN)); // Final Price
                assertEquals(price.getPrice().setScale(2, RoundingMode.HALF_EVEN), BigDecimal.valueOf(argumentsAccessor.getInteger(7)).setScale(2, RoundingMode.HALF_EVEN)); // Price List
            }
        }
    }

}