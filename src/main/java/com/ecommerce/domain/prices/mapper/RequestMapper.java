package com.ecommerce.domain.prices.mapper;

import com.ecommerce.domain.prices.request.PriceRequestContext;
import com.ecommerce.domain.prices.response.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

    /**
     * Metodo responsable de transformar o mappear los campos del request
     * a la respuesta esperada por el contrato
     *
     * @param priceRequestContext
     * @return
     */
    @Mapping(source = "priceRequest.appDate", target = "appDate")
    @Mapping(source = "priceRequest.brandId", target = "brandId")
    @Mapping(source = "price.finalPrice", target = "finalPrice")
    @Mapping(source = "price.priceList", target = "price")
    @Mapping(source = "price.productId", target = "productId")
    PriceResponse of(PriceRequestContext priceRequestContext);

}
