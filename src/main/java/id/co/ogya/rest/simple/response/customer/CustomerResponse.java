package id.co.ogya.rest.simple.response.customer;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerResponse {
	private Long id;
	private String generatedId;
	private String name;
	private String email;
	private String address;
	private Date date;
	private Purchase purchase;
}
