package id.co.ogya.rest.simple.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import id.co.ogya.rest.simple.util.ClientUtils;
import id.co.ogya.rest.simple.ext.feign.CustomerOpenFeign;
import com.google.gson.FieldNamingPolicy;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        jacksonConverter.setObjectMapper(objectMapper);

        // Replace the default Jackson converter with the custom one
        restTemplate.getMessageConverters().add(0, jacksonConverter);
        return  restTemplate;
    }

}
