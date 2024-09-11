package id.co.ogya.rest.simple.request.customer;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class CustomerRequest {
	@NotBlank
	private String email;
}
