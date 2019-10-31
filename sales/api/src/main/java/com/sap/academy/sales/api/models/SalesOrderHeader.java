package com.sap.academy.sales.api.models;

import com.sap.academy.sales.api.commons.EventHeader;

import java.time.LocalDate;
import java.util.Set;

public class SalesOrderHeader extends EventHeader {
    String documentId;
    String salesDocumentNumber;
    LocalDate documentCreationDate;
    LocalDate quotationValidFrom;
    Double netValue;
    String currency;
    String salesOrganization;
    private Set<SalesOrderItem> items;

    public SalesOrderHeader() {
    }

    public SalesOrderHeader(String documentId, String salesDocumentNumber, LocalDate documentCreationDate, LocalDate quotationValidFrom, Double netValue, String currency, String salesOrganization, Set<SalesOrderItem> items) {
        this.documentId = documentId;
        this.salesDocumentNumber = salesDocumentNumber;
        this.documentCreationDate = documentCreationDate;
        this.quotationValidFrom = quotationValidFrom;
        this.netValue = netValue;
        this.currency = currency;
        this.salesOrganization = salesOrganization;
        this.items = items;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Set<SalesOrderItem> getItems() {
        return items;
    }

    public void setItems(Set<SalesOrderItem> items) {
        this.items = items;
    }

    public String getSalesDocumentNumber() {
        return salesDocumentNumber;
    }

    public void setSalesDocumentNumber(String salesDocumentNumber) {
        this.salesDocumentNumber = salesDocumentNumber;
    }

    public LocalDate getDocumentCreationDate() {
        return documentCreationDate;
    }

    public void setDocumentCreationDate(LocalDate documentCreationDate) {
        this.documentCreationDate = documentCreationDate;
    }

    public LocalDate getQuotationValidFrom() {
        return quotationValidFrom;
    }

    public void setQuotationValidFrom(LocalDate quotationValidFrom) {
        this.quotationValidFrom = quotationValidFrom;
    }

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSalesOrganization() {
        return salesOrganization;
    }

    public void setSalesOrganization(String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }
}
