package com.sofka.hardware.mapper;

import com.sofka.hardware.collection.Product;
import com.sofka.hardware.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProductMapper {

    private final ModelMapper mapper;

    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductDTO toProductDto(Product product){
        return mapper.map(product, ProductDTO.class);
    }

    public Product toProductEntity(ProductDTO productDTO){
        return mapper.map(productDTO, Product.class);
    }
}
