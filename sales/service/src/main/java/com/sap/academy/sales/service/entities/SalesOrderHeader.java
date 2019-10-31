package com.sap.academy.sales.service.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity(name="SALES_ORDER_HEADER")
//@Table(name="SALES_ORDER_HEADER")
public class SalesOrderHeader implements Serializable {
    @Id
    @Column(name="DOCUMENT_ID", length=50, nullable=false, unique=false)
    String documentId;
    @Column(name="SALES_DOCUMENT_NUMBER", length=50, nullable=false, unique=false)
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

    @OneToMany(mappedBy="salesOrderHeader" ,fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private Set<SalesOrderItem> items;

    public SalesOrderHeader(){

    }

    public Set<SalesOrderItem> getItems() {
        return items;
    }

    public void setItems(Set<SalesOrderItem> items) {
        this.items = items;
    }

    public SalesOrderHeader(com.sap.academy.sales.api.models.SalesOrderHeader salesOrderHeader ) {
        this.documentId = salesOrderHeader.getDocumentId();
        this.salesDocumentNumber = salesOrderHeader.getSalesDocumentNumber();
        this.documentCreationDate = salesOrderHeader.getDocumentCreationDate();
        this.quotationValidFrom = salesOrderHeader.getQuotationValidFrom();
        this.netValue = salesOrderHeader.getNetValue();
        this.currency = salesOrderHeader.getCurrency();
        this.salesOrganization = salesOrderHeader.getSalesOrganization();
        this.items = SalesOrderItem.toEntitySet(salesOrderHeader.getItems(),this);
    }

    public SalesOrderHeader(SalesOrderHeader salesOrderHeader ) {
        this.documentId = salesOrderHeader.getDocumentId();
        this.salesDocumentNumber = salesOrderHeader.salesDocumentNumber;
        this.documentCreationDate = salesOrderHeader.documentCreationDate;
        this.quotationValidFrom = salesOrderHeader.quotationValidFrom;
        this.netValue = salesOrderHeader.netValue;
        this.currency = salesOrderHeader.currency;
        this.salesOrganization = salesOrderHeader.salesOrganization;
        this.items = salesOrderHeader.getItems();
    }



    public com.sap.academy.sales.api.models.SalesOrderHeader toModel() {
        com.sap.academy.sales.api.models.SalesOrderHeader salesOrderHeader1 = new com.sap.academy.sales.api.models.SalesOrderHeader();
        salesOrderHeader1.setDocumentId(this.getDocumentId());
        salesOrderHeader1.setSalesDocumentNumber(this.getSalesDocumentNumber());
        salesOrderHeader1.setDocumentCreationDate(this.getDocumentCreationDate());
        salesOrderHeader1.setQuotationValidFrom(this.getQuotationValidFrom());
        salesOrderHeader1.setNetValue(this.getNetValue());
        salesOrderHeader1.setCurrency(this.getCurrency());
        salesOrderHeader1.setSalesOrganization(this.getSalesOrganization());
        salesOrderHeader1.setItems(SalesOrderItem.toModelSet(this.getItems()));
        return salesOrderHeader1;
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
}
