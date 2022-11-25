package com.nopecho.localstack.config;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class SNSConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.sns.end-point}")
    private String snsEndPoint;

    private final AWSCredentialsProvider credentials;

    @Bean
    @Primary
    public AmazonSNSAsync amazonSNSAsyncClient() {
        return AmazonSNSAsyncClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(snsEndPoint,region))
                .withCredentials(credentials)
                .build();
    }
}
