package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ProductDTO;
import com.sofka.hardware.mapper.ProductMapper;
import com.sofka.hardware.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Mono<ProductDTO> updateProduct(String id,ProductDTO productDTO) {
        return productRepository.findById(id).flatMap(product -> {
            productDTO.setIdProduct(product.getIdProduct());
            return productRepository.save(productMapper.toProductEntity(productDTO))
                    .map(productRes -> productMapper.toProductDto(productRes));
        }).switchIfEmpty(Mono.just(new ProductDTO()));
    }
}
