package com.sofka.hardware.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Document(collection = "bill")
public class Bill {
    @Id
    private String id;

    @NotBlank(message="Date can't be be blank")
    private String date;

    @NotBlank(message="Client can't be blank")
    private String client;

    @NotBlank(message="Employee can't be be blank")
    private String employee;

    @NotBlank(message="Products can't be be blank")
    private List<Product> products;

    @NotBlank(message="TotalPaid can't be be blank")
    private String totalPaid;
}
