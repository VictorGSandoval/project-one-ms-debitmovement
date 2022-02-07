package com.microservice.transaction.service;


import org.springframework.stereotype.Service;
import com.microservice.transaction.model.Transaction;
import com.microservice.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {
	
	 private  final TransactionRepository transactionRepository;

	  public Flux<Transaction> getAllTransaction(){
	    return transactionRepository.findAll();
	  }
	  public Mono<Transaction> getTransactionById(String id){
	    return  transactionRepository.findById(id);
	  }
	  public Mono<Transaction> createTransaction(Transaction transaction){
	    return transactionRepository.save(transaction);
	  }
	  public Mono<Transaction> updateTransaction(String id,  Transaction transaction){
	    return transactionRepository.findById(id)
	            .flatMap(bean -> {
	              bean.setAmount(transaction.getAmount());
	              bean.setType(transaction.getType());
	              bean.setDateTransaction(transaction.getDateTransaction());
	              bean.setStatusTransaction(transaction.getStatusTransaction());
	              bean.setIdAccount(transaction.getIdAccount());
	              return transactionRepository.save(bean);
	            });
	  }
	  public Mono<Transaction> deleteTransaction(String id){
	    return transactionRepository.findById(id)
	            .flatMap(existsAccount -> transactionRepository.delete(existsAccount)
	                    .then(Mono.just(existsAccount)));
	  }


}
