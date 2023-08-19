package co.com.reto.model.account;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private  String number;
    private  String type;
}