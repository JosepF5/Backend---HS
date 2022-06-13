package com.sofka.hardware.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProviderDTO {
    private String idProvider;
    @NotBlank(message="nameProvider cant be blank")
    private String nameProvider;
    @NotBlank(message="dniProvider cant be blank")
    private Integer dniProvider;
    @NotBlank(message="phoneProvider cant be blank")
    private Integer phoneProvider;
}
