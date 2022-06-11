package com.sofka.hardware.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Data
@Document(collection="product")
public class Product {

    @Id
    private String idProduct;
    @NotBlank(message="nameProduct cant be blank")
    private String nameProduct;
    @NotBlank(message="amountProduct cant be blank")
    private Integer amountProduct;
    @NotBlank(message="minAmountProduct cant be blank")
    private Integer minAmountProduct;
    @NotBlank(message="maxAmountProduct cant be blank")
    private Integer maxAmountProduct;
    @NotBlank(message="providersProduct cant be blank")
    private String providersProduct;
    @NotBlank(message="descriptionProduct cant be blank")
    private String descriptionProduct;
    @NotBlank(message="priceProduct cant be blank")
    private Long priceProduct;

}
