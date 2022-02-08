package com.microservice.transaction.controller;


import com.microservice.transaction.model.DebitMovement;
import com.microservice.transaction.service.DebitMovementService;
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
@RequestMapping("/DebitMovement")
public class DebitMovementController {
	
private final DebitMovementService debitMovementService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<DebitMovement>>>getAllDebitMovement() {
		Flux<DebitMovement> list=this.debitMovementService.getAllDebitMovement();
		return  Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<DebitMovement>> getDebitMovementById(@PathVariable String id){
		var debitMovement=this.debitMovementService.getDebitMovementById(id);
		return debitMovement.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<DebitMovement> create(@RequestBody DebitMovement debitMovement){
		return this.debitMovementService.createDebitMovement(debitMovement);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<DebitMovement>> updateDebitMovementById(@PathVariable String id, @RequestBody DebitMovement debitMovement){

		return this.debitMovementService.updateDebitMovement(id,debitMovement)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteDebitMovementById(@PathVariable String id){
		return this.debitMovementService.deleteDebitMovement(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
