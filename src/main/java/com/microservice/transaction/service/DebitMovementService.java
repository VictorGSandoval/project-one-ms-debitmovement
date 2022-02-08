package com.microservice.transaction.service;


import org.springframework.stereotype.Service;
import com.microservice.transaction.model.DebitMovement;
import com.microservice.transaction.repository.DebitMovementRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DebitMovementService {
	
	 private  final DebitMovementRepository debitMovementRepository;

	  public Flux<DebitMovement> getAllDebitMovement(){
	    return debitMovementRepository.findAll();
	  }
	  public Mono<DebitMovement> getDebitMovementById(String id){
	    return  debitMovementRepository.findById(id);
	  }
	  public Mono<DebitMovement> createDebitMovement(DebitMovement debitMovement){return debitMovementRepository.save(debitMovement);
	  }
	  public Mono<DebitMovement> updateDebitMovement(String id, DebitMovement debitMovement){
	    return debitMovementRepository.findById(id)
	            .flatMap(bean -> {
	              bean.setAmount(debitMovement.getAmount());
				  bean.setDate(debitMovement.getDate());
				  bean.setDescription(debitMovement.getDescription());
	              bean.setIdAccountCustomer(debitMovement.getIdAccountCustomer());
				  bean.setIdAccountDestination(debitMovement.getIdAccountDestination());
	              return debitMovementRepository.save(bean);
	            });
	  }
	  public Mono<DebitMovement> deleteDebitMovement(String id){
	    return debitMovementRepository.findById(id)
	            .flatMap(existsMovementRepository -> debitMovementRepository.delete(existsMovementRepository)
	                    .then(Mono.just(existsMovementRepository)));
	  }


}
