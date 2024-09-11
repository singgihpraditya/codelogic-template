package id.co.ogya.rest.simple.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties("simple")
public class SimpleRestConfig {
	private String customerUrl;
	private Integer clientTimeOut;
}
