package com.ecommerce.application.port.in.rest;

import com.ecommerce.domain.prices.PriceService;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Validated
public class PriceController {

    private final PriceService priceService;

    @PostMapping(value = "/price")
    public @ResponseBody ResponseEntity<PriceResponse> getPrice(@Valid @RequestBody PriceRequest priceRequest) {
        PriceResponse price = priceService.getPrice(priceRequest);
        return ResponseEntity.ok(price);
    }

}
