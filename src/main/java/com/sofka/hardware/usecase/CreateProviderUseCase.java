package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ProviderDTO;
import com.sofka.hardware.mapper.ProviderMapper;
import com.sofka.hardware.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateProviderUseCase {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public Mono<ProviderDTO> createProvider(ProviderDTO providerDTO){
        return providerRepository.save(
                providerMapper.toProviderEntity(providerDTO))
                .map(providerMapper::toProviderDto);
    }
}
