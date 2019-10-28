package com.sap.academy.sales.service.entities;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SalesOrderItem {
    @Id
    @Column(name="DOCUMENT_ITEM_ID", length=50, nullable=false, unique=false)
    String documentItemId;
    @Column(name="MATERIAL_NUMBER", length=50, nullable=false, unique=false)
    String materialNumber;
    @Column(name="DESCRIPTION", length=50, nullable=true, unique=false)
    String description;
    @Column(name="QUANTITY", nullable=false, unique=false)
    Float quantity;
    @Column(name="UNIT_OF_MEASURE", length=50, nullable=false, unique=false)
    String unitOfMeasure;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HEADER_DOCUMENT_ID", referencedColumnName = "DOCUMENT_ID")
    private SalesOrderHeader salesOrderHeader;

    public SalesOrderItem() {

    }
}
