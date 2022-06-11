package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.mapper.ProductMapper;
import com.sofka.hardware.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Mono<ProductDTO> createProduct(ProductDTO productDTO){
        return productRepository.save(
                        productMapper.toProductEntity(productDTO))
                .map(productMapper::toProductDto);
    }
}
