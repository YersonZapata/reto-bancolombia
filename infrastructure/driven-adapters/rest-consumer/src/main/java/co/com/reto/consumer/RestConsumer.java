package co.com.reto.consumer;

import co.com.bancolombia.secretsmanager.api.exceptions.SecretException;
import co.com.reto.consumer.config.CloudProperties;
import co.com.reto.consumer.config.Constants;
import co.com.reto.consumer.exceptions.GeneralException;

import co.com.reto.model.account.Accounts;
import co.com.reto.model.account.gateways.AccountsRepository;

import co.com.reto.model.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class RestConsumer implements AccountsRepository// implements Gateway from domain
{

    //private final String url="https://banking-core.free.beeceptor.com/accounts/";

    private Map<String, String> headers=new HashMap<>();
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private final CloudProperties cloudProperties;


    public Accounts getAccounts(String identification) throws IOException {
        String key="";
        String header="";
        String url="";
        try {
            key=cloudProperties.getSecretModel().getHeader();
             header=cloudProperties.getSecretModel().getHeadername();
             url=cloudProperties.getSecretModel().getUrl();
        } catch (SecretException e) {
            throw new GeneralException(Constants.HTTP_CODE_503 +
                    "|" +Constants.CREDENTIAL_PROBLEMS );
        }

        System.out.println(key);
        Request request = new Request.Builder()
                .url(url+identification)
                .get()
                .addHeader(header, key)
                .build();

        return callAndMap(request, Accounts.class);
    }

    private <T> T callAndMap(Request request, Class<T> clazz) throws IOException {
        try {
        Response response = client.newCall(request).execute();
            return responseEvaluater(response,clazz);

    }catch (IOException ex) {
            throw new GeneralException(Constants.HTTP_CODE_500 +
                    "|" +ex.getLocalizedMessage());
        }
    }

    private <T> T responseEvaluater(Response response, Class<T> clazz) throws IOException {
        switch (response.code()){
            case 200:
                if (Objects.equals(response.headers().get(Constants.CONTENT_TYPE), Constants.CONTENT_TYPE_TEXTPLAIN)){
                    //ServiceException e = mapper.readValue(response.body().string(), ServiceException.class);
                    throw new GeneralException(Constants.HTTP_CODE_200 +
                            "|" +response.body().string() );
                } else
                    return mapper.readValue(response.body().string(), clazz);

            case 404:
                //ServiceException e = mapper.readValue(response.body().string(), ServiceException.class);
                throw new GeneralException(Constants.HTTP_CODE_404 +
                        "|" +Constants.USER_NO_FOUND );
            case 401:
                throw new GeneralException(Constants.HTTP_CODE_401 +
                        "|" +Constants.USER_NO_AUTH );

            default:
                ServiceException e = mapper.readValue(response.body().string(), ServiceException.class);
                throw new GeneralException(e.getCode() +
                        response.body().toString() );
        }

    }

}
