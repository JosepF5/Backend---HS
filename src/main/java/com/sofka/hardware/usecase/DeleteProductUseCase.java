package com.sofka.hardware.usecase;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    private Mono<Product> findProductByID(String id){
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteProduct(String id){
        return findProductByID(id)
                .flatMap(p->productRepository.deleteById(p.getIdProduct()));
    }
}
