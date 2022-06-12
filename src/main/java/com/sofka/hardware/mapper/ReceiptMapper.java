package com.sofka.hardware.mapper;

import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.dto.ReceiptDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ReceiptMapper {
    private final ModelMapper mapper;

    public ReceiptMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ReceiptDTO toReceiptDto(Receipt receipt){
        return mapper.map(receipt, ReceiptDTO.class);
    }

    public Receipt toReceiptEntity(ReceiptDTO receiptDto){
        return mapper.map(receiptDto, Receipt.class);
    }
}
