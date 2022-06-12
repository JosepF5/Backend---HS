package com.sofka.hardware.route;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.usecase.CreateProviderUseCase;
import com.sofka.hardware.usecase.CreateProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateProviderRoute {
    @Bean
    @RouterOperation(path = "/create/provider", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = CreateProviderUseCase.class, beanMethod = "createProvider",
            operation = @Operation(operationId = "createProvider", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Provider.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Provider details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Provider.class)))
            ))
    public RouterFunction<ServerResponse> createProvider(CreateProviderUseCase createProviderUseCase){
        return route(POST("/create/provider").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(createProviderUseCase::createProvider)
                        .flatMap(providerDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(providerDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
