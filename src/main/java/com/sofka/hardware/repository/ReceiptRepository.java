package com.sofka.hardware.repository;

import com.sofka.hardware.collection.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReceiptRepository extends ReactiveMongoRepository<Receipt,String> {
}
