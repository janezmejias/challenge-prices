package com.ecommerce.domain.prices.mapper;

import com.ecommerce.domain.prices.request.PriceRequest;
import com.ecommerce.domain.prices.response.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

    @Mapping(source = "appDate", target = "appDate")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "price.finalPrice", target = "finalPrice")
    @Mapping(source = "price.priceList", target = "price")
    @Mapping(source = "price.productId", target = "productId")
    PriceResponse of(PriceRequest priceRequest);

}
