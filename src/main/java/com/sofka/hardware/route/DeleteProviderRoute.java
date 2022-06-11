package com.sofka.hardware.route;

import com.sofka.hardware.usecase.DeleteProviderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProviderRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteStockist(DeleteProviderUseCase deleteProviderUseCase){
        return route(DELETE("/delete/provider/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProviderUseCase.deleteProvider(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
