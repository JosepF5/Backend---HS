package com.sofka.hardware.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
public class ReceiptDTO {

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
