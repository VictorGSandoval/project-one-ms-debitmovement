package com.microservice.transaction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class DebitMovement {

	@Id
	private String id;
	private double amount;
	private String date;
	private String description;
	private int idAccountCustomer;
	private int idAccountDestination;
	

}
