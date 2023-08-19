package co.com.reto.api;

import co.com.reto.api.requests.SaveRequest;
import co.com.reto.model.account.Account;
import co.com.reto.model.account.Accounts;
import co.com.reto.model.client.Client;
import co.com.reto.usecase.client.ClientUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ApiRestTest {

    private Account account1;
    private Account account2;

    private Client client;

    @MockBean
    ClientUseCase clientUseCase;


    @BeforeEach
    void init() {
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
        Accounts accounts = new Accounts(ListAccounts);

        client = Client.builder()
                .identification("1234")
                .accounts(accounts)
                .build();
        //when(clientUseCase.getAccounts(any())).thenReturn(client);
    }


    //ApiRest apiRest=new ApiRest(clientUseCase);

    @Test
    void apiRestGetAccountsTest() throws Exception {
        Mockito.when(clientUseCase.getAccounts(any())).thenReturn(client);
        ApiRest apiRest = new ApiRest(clientUseCase);
        var response = apiRest.clientAccountServices(client.getIdentification());
        assertEquals(response.getStatusCode().value(),200);

    }

    @Test
    void apiRestSaveTest() throws Exception {
        SaveRequest saveRequest = SaveRequest.builder()
                .identification("123")
                .build();
        Mockito.when(clientUseCase.saveAccounts(any())).thenReturn(client);
        ApiRest apiRest = new ApiRest(clientUseCase);
        var response = apiRest.clientSaveServices(saveRequest);
        assertEquals(response.getStatusCode().value(),200);

    }
}