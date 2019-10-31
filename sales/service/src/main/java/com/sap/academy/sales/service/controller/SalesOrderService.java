package com.sap.academy.sales.service.controller;

import com.google.gson.Gson;
import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.publish.SimpleMessagePublisher;
import com.sap.academy.sales.api.commons.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Component
public class SalesOrderService {
    @Autowired
    SalesOrderRepository salesOrderRepository;

    @Autowired
    SimpleMessagePublisher simpleMessagePublisher;

    public com.sap.academy.sales.api.models.SalesOrderHeader createSalesOrder(com.sap.academy.sales.api.models.SalesOrderHeader salesOrderHeader){
        com.sap.academy.sales.service.entities.SalesOrderHeader salesOrderHeader1 = new com.sap.academy.sales.service.entities.SalesOrderHeader(salesOrderHeader);
        salesOrderRepository.save(salesOrderHeader1);
        salesOrderHeader.setEventId(UUID.randomUUID().toString());
        salesOrderHeader.setEventType(Operation.CREATE.toString());
        simpleMessagePublisher.publish("com.sap.academy.production-"+TechnicalContextHolder.getTechnicalContext().getTenantId(),new Gson().toJson(salesOrderHeader).toString());
        return salesOrderHeader;
    }

    public void deleteSalesOrder(String salesOrderHeaderId){
        salesOrderRepository.deleteById(salesOrderHeaderId);
    }

    public com.sap.academy.sales.api.models.SalesOrderHeader getById(@PathVariable(required = true) String id) throws Exception {
        com.sap.academy.sales.api.models.SalesOrderHeader salesOrderHeader = null;
        try {
            Optional<com.sap.academy.sales.service.entities.SalesOrderHeader> salesOrderHeaderOptional = salesOrderRepository.findById(id);
            com.sap.academy.sales.service.entities.SalesOrderHeader salesOrderHeaderTemp = salesOrderHeaderOptional.orElseThrow(()->new Exception("Sales order not found"));
            return salesOrderHeaderTemp.toModel();
        } catch (Exception e) {
            throw e;
        }
    }

}
