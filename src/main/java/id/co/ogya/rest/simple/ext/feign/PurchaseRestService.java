package id.co.ogya.rest.simple.ext.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ogya.rest.simple.response.feign.InquiryCustomerPurcaseOutputSchema;
import id.co.ogya.rest.simple.response.feign.SimpleRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PurchaseRestService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${simple.purchaseUrl}")
    private String url;

    public SimpleRestResponse<InquiryCustomerPurcaseOutputSchema> getCustomerPurchase(Long customerId){
        ResponseEntity<SimpleRestResponse<InquiryCustomerPurcaseOutputSchema>> responseEntity = null;
        try{
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,  new ParameterizedTypeReference<SimpleRestResponse<InquiryCustomerPurcaseOutputSchema>>() {}, customerId);
            log.debug("Response body {}", new ObjectMapper().writeValueAsString(responseEntity.getBody()));

        }
        catch(Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return responseEntity.getBody();
    }
}
