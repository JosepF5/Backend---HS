package com.sofka.hardware;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.BillRepository;
import com.sofka.hardware.repository.ProviderRepository;
import com.sofka.hardware.usecase.CreateBillUseCase;
import com.sofka.hardware.usecase.CreateProviderUseCase;
import com.sofka.hardware.usecase.GetProvidersUseCase;
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
public class CreateProviderUseCaseTest {
    @MockBean
    private CreateProviderUseCase createProviderUseCase;
    @Mock
    ProviderRepository providerRepository;
    @Test
    void createProviderUseCase(){

        ProviderDTO provider = new ProviderDTO();
        provider.setIdProvider("Provider001");
        provider.setNameProvider("Josep Palomino");
        provider.setDniProvider(123456);
        provider.setPhoneProvider(456789);

        StepVerifier.create(Mono.just(Mockito.when(createProviderUseCase.createProvider(provider))
                        .thenReturn(Mono.just(provider))))
                .expectNextCount(1).verifyComplete();
    }
}
