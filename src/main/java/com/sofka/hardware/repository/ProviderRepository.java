package com.sofka.hardware.repository;

import com.sofka.hardware.collection.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProviderRepository extends ReactiveMongoRepository<Provider,String> {

}
