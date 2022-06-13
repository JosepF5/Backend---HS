package com.sofka.hardware;

import com.sofka.hardware.collection.Bill;
import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.mapper.BillMapper;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.mapper.ReceiptMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.repository.ProviderRepository;
import com.sofka.hardware.repository.ReceiptRepository;
import com.sofka.hardware.usecase.GetBillsUseCase;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import com.sofka.hardware.usecase.GetReceiptsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GetReceiptsUseCaseTest {
    private GetReceiptsUseCase getReceiptsUseCase;
    @Autowired
    private ReceiptMapper receiptMapper;
    @Mock
    ReceiptRepository receiptRepository;
    @BeforeEach
    void setUp(){
        getReceiptsUseCase=new GetReceiptsUseCase(receiptRepository,receiptMapper);
    }
    @Test
    void getBillUseCase(){

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
        Receipt receipt = new Receipt();
        receipt.setIdReceipt("Receipt001");
        receipt.setDateReceipt("21/04/2022");
        receipt.setAmountReceipt(10);
        receipt.setProductReceipt(product.getNameProduct());
        receipt.setNameProviderReceipt(provider.getNameProvider());

        Mockito.when(receiptRepository.findAll()).thenReturn(Flux.just(receipt));

        StepVerifier.create(getReceiptsUseCase.getReceipts())
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(receiptRepository).findAll();
    }
}
