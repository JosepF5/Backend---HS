package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.BillDTO;
import com.sofka.hardware.mapper.BillMapper;
import com.sofka.hardware.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetBillsUseCase {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public Flux<BillDTO> getBills(){
        return billRepository
                .findAll()
                .map(billMapper::toBillDto);
    }
}
