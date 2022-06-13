package com.sofka.hardware;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.mapper.ProductMapper;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.ProductRepository;
import com.sofka.hardware.repository.ProviderRepository;
import com.sofka.hardware.usecase.GetProductsUseCase;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetProductsUseCaseTest {
    private GetProductsUseCase getProductsUseCase;
    @Autowired
    private ProductMapper productMapper;
    @Mock
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
        getProductsUseCase=new GetProductsUseCase(productRepository,productMapper);
    }
    @Test
    void getProductUseCase(){

        Provider provider = new Provider();
        provider.setIdProvider("Provider001");
        provider.setNameProvider("Josep Palomino");
        provider.setDniProvider(123456);
        provider.setPhoneProvider(456789);

        Product product = new Product();
        product.setIdProduct("Product001");
        product.setAmountProduct(100);
        product.setPriceProduct((long)10000);
        product.setNameProduct("Inca Kola");
        product.setProvidersProduct(provider.getNameProvider());
        product.setDescriptionProduct("La mejor gaseosa");
        product.setMaxAmountProduct(50);
        product.setMinAmountProduct(150);

        Mockito.when(productRepository.findAll()).thenReturn(Flux.just(product));

        StepVerifier.create(getProductsUseCase.getProducts())
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(productRepository).findAll();
    }
}
