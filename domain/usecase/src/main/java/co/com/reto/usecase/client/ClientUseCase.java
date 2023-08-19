package co.com.reto.usecase.client;

import co.com.reto.model.account.Account;
import co.com.reto.model.account.Accounts;
import co.com.reto.model.account.gateways.AccountsRepository;
import co.com.reto.model.client.Client;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class ClientUseCase {
    private final AccountsRepository accountsRepository;

    public Client getAccounts(@NonNull String identification) {

        Accounts accounts = null;
        try {
            accounts = accountsRepository.getAccounts(identification);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Client.builder()
                .identification(identification)
                .accounts(accounts)
                .build();
    }

    public Client saveAccounts(@NonNull String identification) {
        return getAccounts(identification);
    }

}