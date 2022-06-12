package com.sofka.hardware.dto;

import com.sofka.hardware.collection.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BillDTO {

    private String idBill;

    @NotBlank(message="dateBill can't be be blank")
    private String dateBill;

    @NotBlank(message="clientBill can't be blank")
    private String clientBill;

    @NotBlank(message="employeeBill can't be blank")
    private String employeeBill;

    @NotBlank(message="productsBill can't be blank")
    private List<Product> productsBill;

    @NotBlank(message="paymentBill can't be blank")
    private Integer paymentBill;
}
