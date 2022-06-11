package com.sofka.hardware.usecase;

import com.sofka.hardware.collection.Provider;
import com.sofka.hardware.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteProviderUseCase {
    private final ProviderRepository providerRepository;

    private Mono<Provider> findProviderByID(String id){
        return providerRepository.findById(id)
               .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteProvider(String id){
        return findProviderByID(id)
                .flatMap(p->providerRepository.deleteById(p.getIdProvider()));
    }
}
