package com.sofka.hardware.route;

import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.dto.ReceiptDTO;
import com.sofka.hardware.usecase.GetReceiptsUseCase;
import com.sofka.hardware.usecase.GetReceiptsUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetReceiptsRoute {
    @Bean
    @RouterOperation(path = "/get/receipts", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetReceiptsUseCase.class, method = RequestMethod.GET, beanMethod = "getReceipts",
            operation = @Operation(operationId = "getReceipts", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Receipt.class)))}
            ))
    public RouterFunction<ServerResponse> getReceipts(GetReceiptsUseCase getReceiptsUseCase){
        return route(GET("/get/receipts"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getReceiptsUseCase.getReceipts(), ReceiptDTO.class)));
    }
}
