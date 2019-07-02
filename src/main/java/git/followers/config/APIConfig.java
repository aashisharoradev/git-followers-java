package git.followers.config;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {
    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}