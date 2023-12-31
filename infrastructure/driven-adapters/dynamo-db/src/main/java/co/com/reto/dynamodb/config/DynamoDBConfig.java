package co.com.reto.dynamodb.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import org.springframework.context.annotation.Profile;
import java.util.concurrent.Executors;

@Configuration
public class DynamoDBConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(@Value("${aws.dynamodb.endpoint}") String endpoint) {
        return AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider("default"))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, null))
                .build();
    }

    @Bean
    @Profile({"dev", "cer", "pdn"})
    public AmazonDynamoDB amazonDynamoDBAsync(@Value("${aws.dynamodb.threads}") String threads) {
        return AmazonDynamoDBAsyncClientBuilder
                .standard()
                .withExecutorFactory(() -> Executors.newFixedThreadPool(Integer.parseInt(threads)))
                .build();
    }

}
