package com.microservice.transaction.service;


import com.microservice.transaction.listeneraccount.AccountClient;
import com.microservice.transaction.listeneraccount.AccountWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.stereotype.Service;
import com.microservice.transaction.model.DebitMovement;
import com.microservice.transaction.repository.DebitMovementRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DebitMovementService {
	
	 private  final DebitMovementRepository debitMovementRepository;
	private final static Logger logger= LoggerFactory.getLogger(DebitMovementService.class);


	  public Flux<DebitMovement> getAllDebitMovement(){
		  WebClient webClient = WebClient.create();

		  //webclient
		  var customerWebClient=new AccountWebClient(webClient);
		  var beanMono= customerWebClient.getAccountMono("60323411");
		  logger.info("this is bean Mono: " + beanMono);

		  //httpclient
		  var accountClient=new AccountClient();
		  var beanObject=accountClient.getAccountById("60323411");
		  logger.info("this is bean Object " + beanObject.toString());


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
