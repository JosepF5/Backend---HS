package com.sofka.hardware.route;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProvidersRoute {
    @Bean
    @RouterOperation(path = "/get/providers", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetProvidersUseCase.class, method = RequestMethod.GET, beanMethod = "getProviders",
            operation = @Operation(operationId = "getProviders", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Provider.class)))}
            ))
    public RouterFunction<ServerResponse> getProviders(GetProvidersUseCase getProvidersUseCase){
        return route(GET("/get/providers"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getProvidersUseCase.getProviders(), ProviderDTO.class)));
    }
}
