package id.co.ogya.rest.simple.response.customer;

import id.co.ogya.rest.simple.response.BaseOutputSchema;

import lombok.Data;

@Data
public class ListCustomerOutputSchema extends BaseOutputSchema {
	private CustomerResponse customer;
}
