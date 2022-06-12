package com.sofka.hardware.usecase;

import com.sofka.hardware.dto.ReceiptDTO;
import com.sofka.hardware.mapper.ReceiptMapper;
import com.sofka.hardware.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateReceiptUseCase {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    public Mono<ReceiptDTO> createReceipt(ReceiptDTO receiptDTO){
        return receiptRepository.save(
                        receiptMapper.toReceiptEntity(receiptDTO))
                .map(receiptMapper::toReceiptDto);
    }
}
