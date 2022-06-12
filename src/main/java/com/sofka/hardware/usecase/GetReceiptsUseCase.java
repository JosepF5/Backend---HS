package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ReceiptDTO;
import com.sofka.hardware.mapper.ReceiptMapper;
import com.sofka.hardware.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetReceiptsUseCase {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    public Flux<ReceiptDTO> getReceipts(){
        return receiptRepository
                .findAll()
                .map(receiptMapper::toReceiptDto);
    }
}
