package co.com.reto.usecase.client;

import co.com.reto.model.account.Account;
import co.com.reto.model.account.Accounts;
import co.com.reto.model.account.gateways.AccountsRepository;
import co.com.reto.model.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

public class ClientUseCaseTest {
    private Account account1;
    private Account account2;
    private Accounts accounts;
    private Client client;
    @InjectMocks
    private ClientUseCase clientUseCase;

    @Mock
    private AccountsRepository accountsRepository;

    @BeforeEach
    void Init() {
        MockitoAnnotations.openMocks(this);
        account1 = Account.builder()
                .number("abc")
                .type("personal")
                .build();

        account2 = Account.builder()
                .number("abc")
                .type("personal")
                .build();
        ArrayList<Account> ListAccounts = new ArrayList<>();
        ListAccounts.add(account2);
        ListAccounts.add(account1);
        accounts = new Accounts(ListAccounts);

        client = Client.builder()
                .identification("1234")
                .accounts(accounts)
                .build();
    }

    @Test
    void getAccountsUseCase() throws IOException {
        Mockito.when(accountsRepository.getAccounts(any())).thenReturn(accounts);
        Assertions.assertEquals(client,clientUseCase.getAccounts("1234"));
    }

    @Test
    void saveAccountsUseCase() throws IOException {
        Mockito.when(accountsRepository.getAccounts(any())).thenReturn(accounts);
        Assertions.assertEquals(client,clientUseCase.saveAccounts("1234"));
    }
}
