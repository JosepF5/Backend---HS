package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.mapper.ProductMapper;
import com.sofka.hardware.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetProductsUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Flux<ProductDTO> getProducts(){
        return productRepository
                .findAll()
                .map(productMapper::toProductDto);
    }
}
