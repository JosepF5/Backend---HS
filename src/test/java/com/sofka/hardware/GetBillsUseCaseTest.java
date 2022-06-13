package com.sofka.hardware;

import com.sofka.hardware.collection.Bill;
import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.mapper.BillMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.usecase.GetBillsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.*;

@SpringBootTest
public class GetBillsUseCaseTest {
    private GetBillsUseCase getBillsUseCase;
    @Autowired
    private BillMapper billMapper;
    @Mock
    BillRepository billRepository;
    @BeforeEach
    void setUp(){
        getBillsUseCase=new GetBillsUseCase(billRepository,billMapper);
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
        Bill bill = new Bill();
        bill.setIdBill("Bill001");
        bill.setDateBill("22/04/2022");
        bill.setEmployeeBill("Fernando");
        bill.setPaymentBill(200);
        bill.setProductsBill(products);
        bill.setClientBill("Josep Palomino");

        Mockito.when(billRepository.findAll()).thenReturn(Flux.just(bill));

        StepVerifier.create(getBillsUseCase.getBills())
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(billRepository).findAll();
    }
}
