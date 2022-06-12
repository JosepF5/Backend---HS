package com.sofka.hardware.usecase;

import com.sofka.hardware.collection.Receipt;
import com.sofka.hardware.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteReceiptUseCase {
    private final ReceiptRepository receiptRepository;

    private Mono<Receipt> findReceiptByID(String id){
        return receiptRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteReceipt(String id){
        return findReceiptByID(id)
                .flatMap(p->receiptRepository.deleteById(p.getIdReceipt()));
    }
}
