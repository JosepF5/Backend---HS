package com.sofka.hardware;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.dto.ReceiptDTO;
import com.sofka.hardware.mapper.ReceiptMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.repository.ReceiptRepository;
import com.sofka.hardware.usecase.CreateBillUseCase;
import com.sofka.hardware.usecase.CreateReceiptUseCase;
import com.sofka.hardware.usecase.GetReceiptsUseCase;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CreateReceiptUseCaseTest {
    @MockBean
    private CreateReceiptUseCase createReceiptUseCase;
    @Mock
    ReceiptRepository receiptRepository;
    @Test
    void createReceiptUseCase(){

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

        List<Product> products=new ArrayList<Product>();
        products.add(product);
        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setIdReceipt("Receipt001");
        receipt.setDateReceipt("21/04/2022");
        receipt.setAmountReceipt(10);
        receipt.setProductReceipt(product.getNameProduct());
        receipt.setNameProviderReceipt(provider.getNameProvider());

        StepVerifier.create(Mono.just(Mockito.when(createReceiptUseCase.createReceipt(receipt))
                        .thenReturn(Mono.just(receipt))))
                .expectNextCount(1).verifyComplete();
    }
}
