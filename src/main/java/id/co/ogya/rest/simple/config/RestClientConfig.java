package id.co.ogya.rest.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import id.co.ogya.rest.simple.util.ClientUtils;
import id.co.ogya.rest.simple.util.CustomerOpenFeign;
import com.google.gson.FieldNamingPolicy;

@Configuration
public class RestClientConfig {

    private SimpleRestConfig config;

    public RestClientConfig(
            SimpleRestConfig config
    ) {
        this.config = config;
    }

    @Bean
    public CustomerOpenFeign customerOpenFeign() {
        return ClientUtils.getHttpClient(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES, config.getClientTimeOut()).target(CustomerOpenFeign.class, config.getCustomerUrl());
    }
}
