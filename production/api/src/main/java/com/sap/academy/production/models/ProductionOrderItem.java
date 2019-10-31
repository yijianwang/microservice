package com.sap.academy.production.models;

public class ProductionOrderItem {
    String documentItemId;
    String materialNumber;
    String description;
    Float quantity;
    String unitOfMeasure;
    String salesOrderNumber;

    public ProductionOrderItem() {
    }

    public ProductionOrderItem(String documentItemId, String materialNumber, String description, Float quantity, String unitOfMeasure, String salesOrderNumber) {
        this.documentItemId = documentItemId;
        this.materialNumber = materialNumber;
        this.description = description;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getDocumentItemId() {
        return documentItemId;
    }

    public void setDocumentItemId(String documentItemId) {
        this.documentItemId = documentItemId;
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

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }
}
