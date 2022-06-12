package com.sofka.hardware.usecase;

import com.sofka.hardware.collection.Bill;
import com.sofka.hardware.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteBillUseCase {
    private final BillRepository billRepository;

    private Mono<Bill> findBillByID(String id){
        return billRepository.findById(id)
                .switchIfEmpty(Mono.error(()->new Throwable("The ID hasnt been found")));
    }

    public Mono<Void> deleteBill(String id){
        return findBillByID(id)
                .flatMap(p->billRepository.deleteById(p.getIdBill()));
    }
}
