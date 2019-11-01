package com.sap.academy.production.controller;
import com.google.gson.Gson;
import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.publish.SimpleMessagePublisher;
import com.sap.academy.production.commons.Operation;
import com.sap.academy.production.entities.ProductionOrderHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.academy.production.models.ProductionOrderItem;
import com.sap.academy.sales.api.commons.EventHeader;
import com.sap.academy.sales.api.models.SalesOrderHeader;


import java.time.LocalDate;

@Component
public class ProductionOrderService {
    @Autowired
    ProductionOrderRepository productionOrderRepository;

    public com.sap.academy.production.models.ProductionOrderHeader saveProductionOrder(com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader){
        ProductionOrderHeader productionOrderHeader1 = new ProductionOrderHeader(productionOrderHeader);
        productionOrderRepository.save(productionOrderHeader1);
        return productionOrderHeader;
    }

    public void deleteProductionOrder(String salesOrderHeaderId){
        productionOrderRepository.deleteById(salesOrderHeaderId);
    }

    public com.sap.academy.production.models.ProductionOrderHeader closeProductionOrder(String id) throws Exception {
        com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader = getById(id);
        productionOrderHeader.setStatus("CLOSED");
        saveProductionOrder(productionOrderHeader);
        return productionOrderHeader;
    }

    public com.sap.academy.production.models.ProductionOrderHeader getById( String id) throws Exception {
        com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader = null;
        try {
            Optional<com.sap.academy.production.entities.ProductionOrderHeader> productionOrderHeaderOptional = productionOrderRepository.findById(id);
            com.sap.academy.production.entities.ProductionOrderHeader productionOrderHeader1 = productionOrderHeaderOptional.orElseThrow(()->new Exception("Production order not found"));
            return productionOrderHeader1.toModel();
        } catch (Exception e) {
            throw e;
        }
    }

    public void processMessage(String message) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        EventHeader eventHeader = objectMapper.readValue(message, EventHeader.class);
        if(eventHeader.getEventType().equalsIgnoreCase(Operation.CREATE.toString())){
            createProductionOrderFromSalesOrder(message);
        }
    }

    private void createProductionOrderFromSalesOrder(String message) throws Exception{
/*      ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.findAndRegisterModules();*/
        Gson gson = new Gson();
        SalesOrderHeader salesOrderHeader = gson.fromJson(message,SalesOrderHeader.class);
        com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader = new com.sap.academy.production.models.ProductionOrderHeader();
        productionOrderHeader.setDocumentId(UUID.randomUUID().toString());
        productionOrderHeader.setStartDate(LocalDate.now());
        productionOrderHeader.setFinishDate(productionOrderHeader.getStartDate().plusDays(10));
        productionOrderHeader.setScheduledStartDate(productionOrderHeader.getStartDate().plusDays(2));
        productionOrderHeader.setOrderNumber(salesOrderHeader.getSalesDocumentNumber());
        productionOrderHeader.setQuantity(0.0);
        productionOrderHeader.setUnitOfMeasure("");
        productionOrderHeader.setStatus("OPEN");
        salesOrderHeader.getItems().forEach(salesOrderItem -> {
            ProductionOrderItem productionOrderItem = new ProductionOrderItem();
            productionOrderItem.setDocumentItemId(UUID.randomUUID().toString());
            productionOrderItem.setMaterialNumber(salesOrderItem.getMaterialNumber());
            productionOrderItem.setDescription(salesOrderItem.getDescription());
            productionOrderItem.setQuantity(salesOrderItem.getQuantity());
            productionOrderItem.setUnitOfMeasure(salesOrderItem.getUnitOfMeasure());
            productionOrderItem.setSalesOrderNumber(salesOrderHeader.getSalesDocumentNumber());
            productionOrderHeader.getItems().add(productionOrderItem);
        });
        saveProductionOrder(productionOrderHeader);
    }
}