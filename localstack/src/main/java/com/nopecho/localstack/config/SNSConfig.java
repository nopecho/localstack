package com.nopecho.localstack.config;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class SNSConfig {

    private final AWSCredentialsProvider credentials;
    private final AwsClientBuilder.EndpointConfiguration endpointConfiguration;

    @Bean
    @Primary
    public AmazonSNSAsync amazonSNSAsyncClient() {
        return AmazonSNSAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentials)
                .build();
    }
}
