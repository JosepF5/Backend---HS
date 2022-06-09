package com.sofka.hardware.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

public class Product {

    private String name;

    private Integer amount;

    private Provider providers;

    private String description;

    private Long price;

    public Product(String name,
                   Integer amount,
                   Provider providers,
                   String description,
                   Long price) {
        this.name = name;
        this.amount = amount;
        this.providers = providers;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Provider getProviders() {
        return providers;
    }

    public void setProviders(Provider providers) {
        this.providers = providers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", providers=" + providers +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
