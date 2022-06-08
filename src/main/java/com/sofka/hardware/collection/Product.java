package com.sofka.hardware.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "product")
public class Product {
    @Id
    private String id;

    private String name;

    private Integer amount;

    private List<String> providers;

    private String description;

    private Integer price;
}
