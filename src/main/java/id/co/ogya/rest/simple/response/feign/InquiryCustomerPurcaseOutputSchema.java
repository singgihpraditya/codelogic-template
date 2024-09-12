package id.co.ogya.rest.simple.response.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.ogya.rest.simple.response.BaseOutputSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryCustomerPurcaseOutputSchema extends BaseOutputSchema {
	private String productId;
	private String productName;
	private Integer quantity;
	private Double price;
}
