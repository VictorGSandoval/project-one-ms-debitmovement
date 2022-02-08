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
public class Transaction {
	
	
	@Id
	private String id;
	private double amount;
	private String type;
	private String dateTransaction;
	private String statusTransaction;
	private int idAccount;
	

}
