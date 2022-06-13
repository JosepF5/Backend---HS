package com.sofka.hardware;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.ProviderRepository;
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
class GetProvidersUseCaseTest {
    private GetProvidersUseCase getProvidersUseCase;
    @Autowired
    private ProviderMapper providerMapper;
    @Mock
    ProviderRepository providerRepository;
    @BeforeEach
    void setUp(){
        getProvidersUseCase=new GetProvidersUseCase(providerRepository,providerMapper);
    }
    @Test
    void getProviderUseCase(){

        Provider provider = new Provider();
        provider.setIdProvider("Provider001");
        provider.setNameProvider("Josep Palomino");
        provider.setDniProvider(123456);
        provider.setPhoneProvider(456789);

        Provider provider2 = new Provider();
        provider.setIdProvider("Provider002");
        provider.setNameProvider("Javier Perez");
        provider.setDniProvider(654321);
        provider.setPhoneProvider(987654);

        Mockito.when(providerRepository.findAll()).thenReturn(Flux.just(provider,provider2));

        StepVerifier.create(getProvidersUseCase.getProviders())
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(providerRepository).findAll();
    }
}
