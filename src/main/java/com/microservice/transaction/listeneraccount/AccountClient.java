package com.microservice.transaction.listeneraccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.microservice.transaction.listeneraccount.dto.Account;
import com.microservice.transaction.utils.JSONUtils;
import com.microservice.transaction.utils.UriAccess;
import com.microservice.transaction.utils.UriBase;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class AccountClient {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();


    public Account getAccountById(String id){
        String url= UriBase.baseUrl+ UriAccess.GET_ACCOUNT_BY_ID +id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        response.thenAccept(System.out::println);
        Account account = null;
        try {
            account= JSONUtils.convertFromJsonToObject(response.get().body(), Account.class);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return account;
    }
}
