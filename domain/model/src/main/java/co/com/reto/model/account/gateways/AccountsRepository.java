package co.com.reto.model.account.gateways;

import co.com.reto.model.account.Accounts;

import java.io.IOException;
import java.util.ArrayList;

public interface AccountsRepository {
    Accounts getAccounts(String identification) throws IOException;
}
