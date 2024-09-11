package id.co.ogya.rest.simple.response.feign;

import id.co.ogya.rest.simple.response.BaseOutputSchema;

import lombok.Data;

@Data
public class InquiryCustomerOutputSchema extends BaseOutputSchema {
	private String name;
	private String email;
	private String address;
}
