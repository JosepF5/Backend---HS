package com.sofka.hardware.route;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.usecase.UpdateProductUseCase;
import com.mongodb.internal.connection.Server;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateProductRoute {
    @Bean
    @RouterOperation(path = "/update/product/{id}"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT, beanClass = UpdateProductUseCase.class, beanMethod = "updateProduct",
            operation = @Operation(operationId = "updateProduct", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Product ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Product not found")}, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "employeeId")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Product.class))))
    )
    RouterFunction<ServerResponse> updateProduct(UpdateProductUseCase updateProductUseCase){
        return route(PUT("/update/product/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> updateProductUseCase.updateProduct(request.pathVariable("id"),productDTO))
                        .flatMap(result -> result.getIdProduct()!=null
                        ? ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result)
                        : ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result)));
    }
}
