package com.sofka.hardware.route;

import com.sofka.hardware.usecase.DeleteBillUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteBillRoute {
    @Bean
    public RouterFunction<ServerResponse> deleteBill(DeleteBillUseCase deleteBillUseCase){
        return route(DELETE("/delete/bill/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteBillUseCase.deleteBill(request.pathVariable("id"))
                        .flatMap((p)-> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}
