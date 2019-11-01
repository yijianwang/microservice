package com.sap.academy.production.models;

import com.sap.academy.production.commons.EventHeader;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

public class ProductionOrderHeader extends EventHeader {
    String documentId;
    String orderNumber;
    LocalDate startDate;
    LocalDate finishDate;
    LocalDate scheduledStartDate;
    Double quantity;
    String unitOfMeasure;
    String status;
    private Set<ProductionOrderItem> items;

    public ProductionOrderHeader() {
        items = new HashSet<>();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(LocalDate scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ProductionOrderItem> getItems() {
        return items;
    }

    public void setItems(Set<ProductionOrderItem> items) {
        this.items = items;
    }
}
