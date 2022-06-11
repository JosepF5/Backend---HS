package com.sofka.hardware.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "provider")
public class Provider {

    @Id
    private String idProvider;
    @NotBlank(message="nameProvider cant be blank")
    private String nameProvider;
    @NotBlank(message="dniProvider cant be blank")
    private Integer dniProvider;
    @NotBlank(message="phoneProvider cant be blank")
    private Integer phoneProvider;
}
