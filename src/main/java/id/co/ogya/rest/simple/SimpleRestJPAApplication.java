package id.co.ogya.rest.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan({"id.co.ogya.rest.*"})
@SpringBootApplication
public class SimpleRestJPAApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleRestJPAApplication.class, args);
	}

}
