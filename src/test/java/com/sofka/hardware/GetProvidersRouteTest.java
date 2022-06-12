package com.sofka.hardware;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.ProviderRepository;
import com.sofka.hardware.route.GetProvidersRoute;
import com.sofka.hardware.usecase.GetProvidersUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={GetProvidersRoute.class, GetProvidersUseCase.class, ProviderMapper.class})
class GetProvidersRouteTest {
    @MockBean
    private ProviderRepository providerRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetProviders(){
        Provider provider1=new Provider();
        provider1.setDniProvider(74119776);
        provider1.setPhoneProvider(748946848);
        provider1.setNameProvider("Josep");
        Provider provider2=new Provider();
        provider1.setDniProvider(78451559);
        provider1.setPhoneProvider(485128654);
        provider1.setNameProvider("Juan");
        Mockito.when(providerRepository.findAll()).thenReturn(Flux.just(provider1, provider2));
        webTestClient.get()
                .uri("/get/providers")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProviderDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getDniProvider()).isEqualTo(provider1.getDniProvider());
                            Assertions.assertThat(userResponse.get(0).getNameProvider()).isEqualTo(provider1.getNameProvider());
                            Assertions.assertThat(userResponse.get(0).getPhoneProvider()).isEqualTo(provider1.getPhoneProvider());
                            Assertions.assertThat(userResponse.get(1).getDniProvider()).isEqualTo(provider2.getDniProvider());
                            Assertions.assertThat(userResponse.get(1).getNameProvider()).isEqualTo(provider2.getNameProvider());
                            Assertions.assertThat(userResponse.get(1).getPhoneProvider()).isEqualTo(provider2.getPhoneProvider());
                        }
                );
    }
}
