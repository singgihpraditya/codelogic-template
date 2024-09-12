package id.co.ogya.rest.simple.response.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Purchase {
    private String productName;
    private Integer quantity;
}
