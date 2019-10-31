package com.sap.academy.sales.service.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="SALES_ORDER_ITEM")
//@Table(name="SALES_ORDER_ITEM")
public class SalesOrderItem implements Serializable {

    @Id
    @Column(name="DOCUMENT_ITEM_ID", length=50, nullable=false, unique=false)
    String documentItemId;
    //@Column(name="HEADER_DOCUMENT_ID", length=50, nullable=false, unique=false)
    //String headerDocumentId;

    @Column(name="MATERIAL_NUMBER", length=50, nullable=false, unique=false)
    String materialNumber;
    @Column(name="DESCRIPTION", length=50, nullable=true, unique=false)
    String description;
    @Column(name="QUANTITY", length=50, nullable=false, unique=false)
    Float quantity;
    @Column(name="UNIT_OF_MEASURE", length=50, nullable=false, unique=false)
    String unitOfMeasure;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HEADER_DOCUMENT_ID", referencedColumnName = "DOCUMENT_ID")
    private SalesOrderHeader salesOrderHeader;

    public SalesOrderItem(){

    }

    public SalesOrderItem(com.sap.academy.sales.api.models.SalesOrderItem salesOrderItem,SalesOrderHeader salesOrderHeader) {
        this.documentItemId = salesOrderItem.getDocumentItemId();
        //this.headerDocumentId = salesOrderItem.getHeaderDocumentId();
        this.materialNumber = salesOrderItem.getMaterialNumber();
        this.description = salesOrderItem.getDescription();
        this.quantity = salesOrderItem.getQuantity();
        this.unitOfMeasure = salesOrderItem.getUnitOfMeasure();
        this.salesOrderHeader = salesOrderHeader;
    }


    public SalesOrderItem(SalesOrderItem salesOrderItem) {
        this.documentItemId = salesOrderItem.getDocumentItemId();
        //this.headerDocumentId = salesOrderItem.getHeaderDocumentId();
        this.materialNumber = salesOrderItem.materialNumber;
        this.description = salesOrderItem.description;
        this.quantity = salesOrderItem.quantity;
        this.unitOfMeasure = salesOrderItem.unitOfMeasure;
    }



    public static Set<SalesOrderItem> toEntitySet(Set<com.sap.academy.sales.api.models.SalesOrderItem> salesOrderItems,SalesOrderHeader salesOrderHeader) {
        return salesOrderItems.stream().map(salesOrderItem -> new SalesOrderItem(salesOrderItem,salesOrderHeader)).collect(Collectors.toSet());
    }


    public static Set<com.sap.academy.sales.api.models.SalesOrderItem> toModelSet(Set<SalesOrderItem> salesOrderItems) {
        return salesOrderItems.stream().map(salesOrderItem -> salesOrderItem.toModelMapper()).collect(Collectors.toSet());
    }

    public com.sap.academy.sales.api.models.SalesOrderItem toModelMapper() {
        com.sap.academy.sales.api.models.SalesOrderItem item = new com.sap.academy.sales.api.models.SalesOrderItem();
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
}
