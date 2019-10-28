package com.sap.academy.sales.service.entities;

import com.sap.academy.sales.service.entities.SalesOrderItem;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class SalesOrderHeader {
    @Id
    @Column(name="DOCUMENT_ID", length=50, nullable=false, unique=false)
    String documentId;
    @Column(name="SALES_DOCUMENT_MEMBER", length=50, nullable=false, unique=false)
    String salesDocumentNumber;
    @Column(name="DOCUMENT_CREATION_DATE", nullable=false, unique=false)
    LocalDate documentCreationDate;
    @Column(name="QUOTATION_VALID_FROM", nullable=false, unique=false)
    LocalDate quotationValidFrom;
    @Column(name="NET_VALUE", nullable=false, unique=false)
    Double netValue;
    @Column(name="CURRENCY", nullable=false, unique=false)
    String currency;
    @Column(name="SALES_ORGANIZATION", nullable=true, unique=false)
    String salesOrganization;

    @OneToMany(mappedBy="salesOrderHeader", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<SalesOrderItem> items;

    public SalesOrderHeader() {

    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public Set<SalesOrderItem> getItems() {
        return items;
    }

    public void setItems(Set<SalesOrderItem> items) {
        this.items = items;
    }


}
