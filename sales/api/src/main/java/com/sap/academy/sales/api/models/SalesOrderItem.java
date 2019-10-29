package com.sap.academy.sales.api.models;

public class SalesOrderItem {
    String documentItemId;
    String headerDocumentId;
    String materialNumber;
    String description;
    Float quantity;
    String unitOfMeasure;

    public SalesOrderItem() {
    }

    public String getDocumentItemId() {
        return documentItemId;
    }

    public void setDocumentItemId(String documentItemId) {
        this.documentItemId = documentItemId;
    }

    public String getHeaderDocumentId() {
        return headerDocumentId;
    }

    public void setHeaderDocumentId(String headerDocumentId) {
        this.headerDocumentId = headerDocumentId;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }


}
