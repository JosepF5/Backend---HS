package com.sofka.hardware.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "receipt")
public class Receipt {
    @Id
    private String idReceipt;
    @NotBlank(message="dateReceipt can't be be blank")
    private String dateReceipt;
    @NotBlank(message="nameProviderReceipt can't be be blank")
    private String nameProviderReceipt;
    @NotBlank(message="productReceipt can't be be blank")
    private String productReceipt;
    @NotBlank(message="amountReceipt can't be be blank")
    private Integer amountReceipt;
}
