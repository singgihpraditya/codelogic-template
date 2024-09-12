package id.co.ogya.rest.simple.service;

import java.util.UUID;

import id.co.ogya.lib.CommonUtils;
import id.co.ogya.rest.simple.ext.feign.PurchaseRestService;
import id.co.ogya.rest.simple.response.customer.Purchase;
import id.co.ogya.rest.simple.response.feign.InquiryCustomerPurcaseOutputSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.co.ogya.rest.simple.entity.Customer;
import id.co.ogya.rest.simple.repository.CustomerRepository;
import id.co.ogya.rest.simple.request.customer.CustomerRequest;
import id.co.ogya.rest.simple.response.customer.CustomerResponse;
import id.co.ogya.rest.simple.response.customer.ListCustomerOutputSchema;
import id.co.ogya.rest.simple.response.feign.SimpleRestResponse;
import id.co.ogya.rest.simple.response.feign.InquiryCustomerOutputSchema;
import id.co.ogya.rest.simple.ext.feign.CustomerOpenFeign;
import id.co.ogya.rest.simple.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	@Autowired
	private ResponseUtils responseUtils;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerOpenFeign customerOpenFeign;
	@Autowired
	private PurchaseRestService purcaseRestService;
	
	public ResponseEntity list(String hashCode, CustomerRequest request) {
		ListCustomerOutputSchema outputSchema = new ListCustomerOutputSchema();
		log.debug(hashCode + "Get data");
		Customer customer = customerRepository.findByEmail(request.getEmail());
		if(customer == null) {
			return responseUtils.generateFailedResult(outputSchema, new Exception("No Data Found"), HttpStatus.NOT_FOUND.value());
		}
		SimpleRestResponse<InquiryCustomerOutputSchema> inquiryCustomerResponse = customerOpenFeign.getResponse(UUID.randomUUID().toString().replace("-", ""), customer.getId());
		InquiryCustomerOutputSchema inquiryCustomerOutputSchema = inquiryCustomerResponse.getOutputSchema();
		if(!inquiryCustomerResponse.getErrorSchema().getErrorCode().equals("INQ-0-000")) {
			log.debug(hashCode+"No data found");
			return responseUtils.generateFailedResult(outputSchema, new Exception("No Data Found"), HttpStatus.NOT_FOUND.value());
		}
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setName(customer.getName());
		customerResponse.setDate(customer.getDate());
		customerResponse.setEmail(customer.getEmail());
		customerResponse.setId(customer.getId());
		customerResponse.setGeneratedId(new CommonUtils().getLongGeneratedId());
		customerResponse.setAddress(inquiryCustomerOutputSchema.getAddress());

		SimpleRestResponse<InquiryCustomerPurcaseOutputSchema> inquiryCustomerPurcaseResponse = purcaseRestService.getCustomerPurchase(customer.getId());
		if(inquiryCustomerPurcaseResponse.getErrorSchema().getErrorCode().equals("PUR-0-000")) {
			InquiryCustomerPurcaseOutputSchema inquiryCustomerPurcaseOutputSchema = inquiryCustomerPurcaseResponse.getOutputSchema();
			Purchase purchase = new Purchase();
			purchase.setQuantity(inquiryCustomerPurcaseOutputSchema.getQuantity());
			purchase.setProductName(inquiryCustomerPurcaseOutputSchema.getProductName());

			customerResponse.setPurchase(purchase);
		}

		outputSchema.setCustomer(customerResponse);
		
		log.debug(hashCode + "Get data done");
		return responseUtils.generateSuccessResult(outputSchema);
	}
}
