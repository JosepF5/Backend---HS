package com.sofka.hardware.mapper;

import com.sofka.hardware.collection.Bill;
import com.sofka.hardware.dto.BillDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BillMapper {

    private final ModelMapper mapper;

    public BillMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BillDTO toBillDto(Bill bill){
        return mapper.map(bill, BillDTO.class);
    }

    public Bill toBillEntity(BillDTO billDTO){
        return mapper.map(billDTO, Bill.class);
    }
}
