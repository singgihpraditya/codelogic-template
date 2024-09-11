package id.co.ogya.rest.simple.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.co.ogya.rest.simple.request.customer.CustomerRequest;
import id.co.ogya.rest.simple.service.CustomerService;
import id.co.ogya.rest.simple.util.Utils;

@RestController
public class CustomerMaintenanceController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/consumer/inquiry", method = RequestMethod.POST)
	public ResponseEntity inquiry(@Valid @RequestBody(required = true) CustomerRequest customerRequest) {
		String hashCode = Utils.getHashCodeNumber() + "-INQUIRY-CUSTOMER-";
		return customerService.list(hashCode, customerRequest);
	}
}
