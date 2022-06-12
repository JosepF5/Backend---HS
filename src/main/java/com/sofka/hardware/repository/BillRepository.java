package com.sofka.hardware.repository;

import com.sofka.hardware.collection.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill,String> {
}
