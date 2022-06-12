package com.sofka.hardware.route;

import com.sofka.hardware.usecase.DeleteProviderUseCase;
import com.sofka.hardware.usecase.DeleteProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
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
public class DeleteProviderRoute {

    @Bean
    @RouterOperation(path = "/swagger-demo/provider/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteProviderUseCase.class, beanMethod = "deleteProvider"
            , operation = @Operation(operationId = "deleteProvider", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "Boolean")),
            @ApiResponse(responseCode = "400", description = "Invalid Provider ID supplied"),
            @ApiResponse(responseCode = "404", description = "Provider not found")}, parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id")}
    ))
    public RouterFunction<ServerResponse> deleteProvider(DeleteProviderUseCase deleteProviderUseCase){
        return route(DELETE("/delete/provider/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProviderUseCase.deleteProvider(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
