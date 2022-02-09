package com.microservice.transaction.listeneraccount;

import com.microservice.transaction.utils.UriAccess;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import com.microservice.transaction.listeneraccount.dto.Account;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountWebClient {
    private final WebClient webClient;

    public Mono<Account> getAccountMono(String id) {
        return this.webClient.get().uri(UriAccess.GET_ACCOUNT_BY_ID, id)
                .retrieve().bodyToMono(Account.class);
    }

    public Account getAccountById(String id) {
        return this.webClient.get().uri(UriAccess.GET_ACCOUNT_BY_ID, id)
                .retrieve()
                .bodyToMono(Account.class).block();
    }
}