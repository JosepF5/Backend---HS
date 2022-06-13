package com.sofka.hardware;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.mapper.ProductMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.repository.ProductRepository;
import com.sofka.hardware.usecase.CreateBillUseCase;
import com.sofka.hardware.usecase.CreateProductUseCase;
import com.sofka.hardware.usecase.GetProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class CreateProductUseCaseTest {
    @MockBean
    private CreateProductUseCase createProductUseCase;
    @Mock
    ProductRepository productRepository;
    @Test
    void createProductUseCase(){

        Provider provider = new Provider();
        provider.setIdProvider("Provider001");
        provider.setNameProvider("Josep Palomino");
        provider.setDniProvider(123456);
        provider.setPhoneProvider(456789);

        ProductDTO product = new ProductDTO();
        product.setIdProduct("Product001");
        product.setAmountProduct(100);
        product.setPriceProduct((long)10000);
        product.setNameProduct("Inca Kola");
        product.setProvidersProduct(provider.getNameProvider());
        product.setDescriptionProduct("La mejor gaseosa");
        product.setMaxAmountProduct(50);
        product.setMinAmountProduct(150);

        StepVerifier.create(Mono.just(Mockito.when(createProductUseCase.createProduct(product))
                        .thenReturn(Mono.just(product))))
                .expectNextCount(1).verifyComplete();
    }
}
