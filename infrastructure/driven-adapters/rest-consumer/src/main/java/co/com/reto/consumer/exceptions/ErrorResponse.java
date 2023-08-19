package co.com.reto.consumer.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponse {

    private String state;
    private String message;
}
