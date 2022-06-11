package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetProvidersUseCase {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public Flux<ProviderDTO> getProviders(){
        return providerRepository
                .findAll()
                .map(providerMapper::toProviderDto);
    }
}
