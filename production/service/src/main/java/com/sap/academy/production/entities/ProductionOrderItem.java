package com.sap.academy.production.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="PRODUCTION_ORDER_ITEM")
public class ProductionOrderItem {
    @Id
    @Column(name = "DOCUMENT_ITEM_ID", length = 50, nullable = false, unique = false)
    String documentItemId;
    @Column(name = "MATERIAL_NUMBER", length = 40, nullable = false, unique = false)
    String materialNumber;
    @Column(name = "DESCRIPTION", length = 100, nullable = false, unique = false)
    String description;
    @Column(name = "QUANTITY", nullable = false, unique = false)
    Float quantity;
    @Column(name = "UNIT_OF_MEASURE", length = 10, nullable = false, unique = false)
    String unitOfMeasure;
    @Column(name = "SALES_ORDER_NUMBER", length = 50, nullable = false, unique = false)
    String salesOrderNumber;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="HEADER_DOCUMENT_ID", referencedColumnName = "DOCUMENT_ID")
    private ProductionOrderHeader productionOrderHeader;

    public ProductionOrderItem(com.sap.academy.production.models.ProductionOrderItem productionOrderItem, ProductionOrderHeader productionOrderHeader) {
        this.documentItemId = productionOrderItem.getDocumentItemId();
        //this.headerDocumentId = productionOrderItem.getHeaderDocumentId();
        this.materialNumber = productionOrderItem.getMaterialNumber();
        this.description = productionOrderItem.getDescription();
        this.quantity = productionOrderItem.getQuantity();
        this.unitOfMeasure = productionOrderItem.getUnitOfMeasure();
        this.productionOrderHeader = productionOrderHeader;
    }

    public ProductionOrderItem(ProductionOrderItem productionOrderItem) {
        this.documentItemId = productionOrderItem.getDocumentItemId();
        //this.headerDocumentId = productionOrderItem.getHeaderDocumentId();
        this.materialNumber = productionOrderItem.materialNumber;
        this.description = productionOrderItem.description;
        this.quantity = productionOrderItem.quantity;
        this.unitOfMeasure = productionOrderItem.unitOfMeasure;
    }

    public static Set<ProductionOrderItem> toEntitySet(Set<com.sap.academy.production.models.ProductionOrderItem> productionOrderItems, ProductionOrderHeader productionOrderHeader) {
        return productionOrderItems.stream().map(productionOrderItem -> new ProductionOrderItem(productionOrderItem, productionOrderHeader)).collect(Collectors.toSet());
    }

    public static Set<com.sap.academy.production.models.ProductionOrderItem> toModelSet(Set<ProductionOrderItem> productionOrderItems) {
        return productionOrderItems.stream().map(productionOrderItem -> productionOrderItem.toModelMapper()).collect(Collectors.toSet());
    }

    public com.sap.academy.production.models.ProductionOrderItem toModelMapper() {
        com.sap.academy.production.models.ProductionOrderItem item = new com.sap.academy.production.models.ProductionOrderItem();
        item.setDocumentItemId(this.getDocumentItemId());
        //item.setHeaderDocumentId(this.getHeaderDocumentId());
        item.setMaterialNumber(this.getMaterialNumber());
        item.setDescription(this.getDescription());
        item.setQuantity(this.getQuantity());
        item.setUnitOfMeasure(this.getUnitOfMeasure());
        return item;
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

    public ProductionOrderHeader getProductionOrderHeader() {
        return productionOrderHeader;
    }

    public void setProductionOrderHeader(ProductionOrderHeader productionOrderHeader) {
        this.productionOrderHeader = productionOrderHeader;
    }
}
