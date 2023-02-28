package com.ecommerce.application.port.in.rest;

import com.ecommerce.domain.prices.PriceService;
import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping(value = "/price")
    public @ResponseBody ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest priceRequest) {
        Optional<PriceResponse> price = priceService.getPrice(priceRequest);
        return price.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
