package co.com.reto.config;


import co.com.bancolombia.secretsmanager.api.GenericManagerAsync;
import co.com.bancolombia.secretsmanager.config.AWSSecretsManagerConfig;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class SecretsConfig {

  @Bean()
  @Profile("!local")
  public GenericManagerAsync getSecretManager(
          @Value("${aws.region}") String region) {
    return new AWSSecretManagerConnectorAsync(getConfig(region));
  }

  @Bean()
  @Profile("local")
  public GenericManagerAsync getSecretManagerLocal(
          @Value("${aws.region}") String region,
          @Value("${aws.localstack}") String endpoint) {
    return new AWSSecretManagerConnectorAsync(getConfigLocal(region, endpoint));
  }

  private AWSSecretsManagerConfig getConfig(String region) {
    return AWSSecretsManagerConfig.builder()
            .build();
  }
  private AWSSecretsManagerConfig getConfigLocal(String region, String endpoint){
    return AWSSecretsManagerConfig.builder()
            .endpoint(endpoint)
            .build();
  }
}