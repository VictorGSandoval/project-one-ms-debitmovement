package com.microservice.transaction.controller;


import com.microservice.transaction.model.Transaction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
	
private final com.microservice.transaction.service.TransactionService transactionService;
	
	@GetMapping(path = "/list")
	public Mono<ResponseEntity<Flux<Transaction>>>getAllAccount() {
		Flux<Transaction> list=this.transactionService.getAllTransaction();
		return  Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}

	@GetMapping("/details/{id}")
	public Mono<ResponseEntity<Transaction>> getAccountById(@PathVariable String id){
		var account=this.transactionService.getTransactionById(id);
		return account.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Transaction> create(@RequestBody Transaction account){
		return this.transactionService.createTransaction(account);
	}

	@PutMapping("update/{id}")
	public Mono<ResponseEntity<Transaction>> updateAccountById(@PathVariable String id, @RequestBody Transaction transaction){

		return this.transactionService.updateTransaction(id,transaction)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("delete/{id}")
	public Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable String id){
		return this.transactionService.deleteTransaction(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
