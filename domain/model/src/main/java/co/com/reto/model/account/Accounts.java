package co.com.reto.model.account;

import lombok.*;

import java.util.ArrayList;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    private ArrayList<Account> accounts;
}
