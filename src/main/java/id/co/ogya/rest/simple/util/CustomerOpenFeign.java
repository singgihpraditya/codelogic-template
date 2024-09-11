package id.co.ogya.rest.simple.util;

import id.co.ogya.rest.simple.response.feign.SimpleRestResponse;
import id.co.ogya.rest.simple.response.feign.InquiryCustomerOutputSchema;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CustomerOpenFeign {
	@Headers(value = { "Content-Type: application/json", "client-id: {client-id}" })
	@RequestLine("GET /simulator/consumer/{id}")
	public SimpleRestResponse<InquiryCustomerOutputSchema> getResponse(@Param("client-id") String clientId, @Param("id") Long id);

}
