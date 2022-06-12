package com.sofka.hardware.route;

import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.dto.ReceiptDTO;
import com.sofka.hardware.usecase.CreateReceiptUseCase;
import com.sofka.hardware.usecase.CreateReceiptUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateReceiptRoute {
    @Bean
    @RouterOperation(path = "/create/receipt", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = CreateReceiptUseCase.class, beanMethod = "createReceipt",
            operation = @Operation(operationId = "createReceipt", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Receipt.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Receipt details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Receipt.class)))
            ))
    public RouterFunction<ServerResponse> createReceipt(CreateReceiptUseCase createReceiptUseCase){
        return route(POST("/create/receipt").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDTO.class)
                        .flatMap(createReceiptUseCase::createReceipt)
                        .flatMap(receiptDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(receiptDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}
