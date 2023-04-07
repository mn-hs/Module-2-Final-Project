package com.techelevator.dao;

import java.math.BigDecimal;

public class Tax {

    private BigDecimal salesTax;
    private String lastUpdated;

    public Tax(){}

    public Tax(BigDecimal salesTax, String lastUpdated) {
        this.salesTax = salesTax;
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getSalesTax() {return salesTax;}

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
