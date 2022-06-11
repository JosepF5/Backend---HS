package com.sofka.hardware.route;

import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProvidersRoute {
    @Bean
    public RouterFunction<ServerResponse> AllStockist(GetProvidersUseCase getProvidersUseCase){
        return route(GET("/get/providers"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getProvidersUseCase.getProviders(), ProviderDTO.class)));
    }
}
