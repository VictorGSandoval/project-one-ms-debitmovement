package com.microservice.transaction.repository;

import com.microservice.transaction.model.DebitMovement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitMovementRepository extends ReactiveCrudRepository<DebitMovement, String> {

}
