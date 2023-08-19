package co.com.reto.model.client;
import co.com.reto.model.account.Account;
import co.com.reto.model.account.Accounts;
import lombok.*;

import java.util.ArrayList;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private  String identification;
    private  Accounts accounts;
}
