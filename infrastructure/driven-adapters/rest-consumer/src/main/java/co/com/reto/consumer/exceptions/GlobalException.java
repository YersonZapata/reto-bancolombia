package co.com.reto.consumer.exceptions;

import co.com.reto.model.exception.ServiceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<ServiceException> handlerResourceGeneralException(
            GeneralException ex) {

        String[] e = ex.getMessage().split("\\|");
        ServiceException serviceException = ServiceException.builder()
                .code(e[0])
                .message(e[1])
                .build();

        return new ResponseEntity<>(serviceException, HttpStatus.valueOf(Integer.parseInt(e[0])));
    }
}
