package com.sofka.hardware.route;

import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.usecase.GetProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProductsRoute {
    @Bean
    public RouterFunction<ServerResponse> getProducts(GetProductsUseCase getProductsUseCase){
        return route(GET("/get/products"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getProductsUseCase.getProducts(), ProductDTO.class)));
    }
}
