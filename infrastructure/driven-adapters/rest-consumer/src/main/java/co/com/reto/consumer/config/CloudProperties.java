package co.com.reto.consumer.config;

import co.com.bancolombia.secretsmanager.api.GenericManagerAsync;
import co.com.bancolombia.secretsmanager.api.exceptions.SecretException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CloudProperties {

    public static final String AWS_SECRET_MANAGER_ASYNC = "awsSecretManagerAsyncConnector";
    @Qualifier(value = AWS_SECRET_MANAGER_ASYNC)
    private final GenericManagerAsync connector;
    @Value("${aws.secretName}") // TODO: NO OLVIDAR CAMBIAR VARIABLE DE LOCALSTACK
    private String secretName;

    @Bean
    public CloudModel getSecretModel() throws SecretException {

      return connector.getSecret(secretName, CloudModel.class).block();
    }
}