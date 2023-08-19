package co.com.reto.api;
import co.com.reto.api.requests.SaveRequest;
import co.com.reto.usecase.client.ClientUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;

    private final ClientUseCase clientUsesCase;


    //@GetMapping(path = "/client/{identification}")
    @ResponseBody
    @RequestMapping(value = "/client/{identification}", method = RequestMethod.GET)
    public ResponseEntity<Object> clientAccountServices(@PathVariable String identification) {
        return new ResponseEntity<> (clientUsesCase.getAccounts(identification), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Object> clientSaveServices(@RequestBody SaveRequest saveRequest) {
        return new ResponseEntity<> (clientUsesCase.saveAccounts(saveRequest.getIdentification()), HttpStatus.OK);
    }
}
