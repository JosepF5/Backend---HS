package com.sofka.hardware;

import com.sofka.hardware.collection.Bill;
import com.sofka.hardware.collection.Product;
import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.BillDTO;
import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.BillMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.usecase.CreateBillUseCase;
import com.sofka.hardware.usecase.CreateProviderUseCase;
import com.sofka.hardware.usecase.GetBillsUseCase;
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
public class CreateBillUseCaseTest {
    @MockBean
    private CreateBillUseCase createBillUseCase;
    @Mock
    BillRepository billRepository;
    @Test
    void createBillUseCase(){

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
        BillDTO bill = new BillDTO();
        bill.setIdBill("Bill001");
        bill.setDateBill("22/04/2022");
        bill.setEmployeeBill("Fernando");
        bill.setPaymentBill(200);
        bill.setProductsBill(products);
        bill.setClientBill("Josep Palomino");

        StepVerifier.create(Mono.just(Mockito.when(createBillUseCase.createBill(bill))
                .thenReturn(Mono.just(bill))))
                .expectNextCount(1).verifyComplete();

    }
}
