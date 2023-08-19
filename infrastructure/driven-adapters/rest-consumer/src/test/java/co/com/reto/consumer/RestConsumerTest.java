package co.com.reto.consumer;

import co.com.bancolombia.secretsmanager.api.exceptions.SecretException;
import co.com.reto.consumer.config.CloudModel;
import co.com.reto.consumer.config.CloudProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import java.io.IOException;
import java.nio.channels.Channel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RestConsumerTest {

    private static RestConsumer restConsumer;

    private static MockWebServer mockBackEnd;

    @InjectMocks
    CloudProperties cloudProperties=mock(CloudProperties.class, Mockito.RETURNS_DEEP_STUBS);



    @BeforeEach
    void init() throws IOException, SecretException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        CloudModel cloudModel = CloudModel.builder()
                .url("https://banking-core.free.beeceptor.com/accounts/")
                .header("1234")
                .headername("api-key")
                .build();


        Mockito.when(this.cloudProperties.getSecretModel()).thenReturn(cloudModel);
        OkHttpClient client = new OkHttpClient.Builder().build();

        restConsumer = new RestConsumer(client, new ObjectMapper(),cloudProperties);
        //String url = mockBackEnd.url("https://banking-core.free.beeceptor.com/accounts/123").toString();
        //ReflectionTestUtils.setField(restConsumer, "https://banking-core.free.beeceptor.com/accounts/123", url);


    }

    @AfterAll
    static void tearDown() throws IOException {

        mockBackEnd.shutdown();
    }

    private CloudModel getSecretModel() {
        return CloudModel.builder()
                .header("1234")
                .headername("api-key")
                .build();
    }

    @Test
    @DisplayName("Validate the function testGet.")
    void validateTestGet() throws IOException, SecretException {


        mockBackEnd.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody("{\n" +
                        "    \"accounts\": [\n" +
                        "        {\n" +
                        "            \"number\": \"1122334455\",\n" +
                        "            \"type\": \"AHO\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"number\": \"8877664433\",\n" +
                        "            \"type\": \"CTE\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"));
        Mockito.when(cloudProperties.getSecretModel()).thenReturn(getSecretModel());

        var response = restConsumer.getAccounts("123");

        Assertions.assertEquals("1122334455", response.getAccounts().get(0).getNumber());

    }

}