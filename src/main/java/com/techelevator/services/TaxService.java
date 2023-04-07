package com.techelevator.services;

import com.techelevator.dao.Tax;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxService {

    RestTemplate restTemplate = new RestTemplate();

    private final BigDecimal TAX_DIVISOR = new BigDecimal(100);

    public BigDecimal getSalesTax(String stateCode){
        Tax response = restTemplate.getForObject("https://teapi.netlify.app/api/statetax?state=" + stateCode,
                Tax.class);
        return response.getSalesTax().divide(TAX_DIVISOR);
    }
}
