package com.microservice.transaction.listeneraccount.dto;


import lombok.Data;

@Data
public class Account {
    private String id;
    private String typeAccount;
    private String numberAccount;
    private int keyAccount;
    private double availableBalanceAccount;
    private String dateCreationAccount;
    private String statusAccount;
    private int idClerkCreation;
    private int idCustomer;
}
