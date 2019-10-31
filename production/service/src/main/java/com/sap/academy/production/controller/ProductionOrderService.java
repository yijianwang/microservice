package com.sap.academy.production.controller;
import com.google.gson.Gson;
import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.publish.SimpleMessagePublisher;
import com.sap.academy.production.commons.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.UUID;
@Component
public class ProductionOrderService {
    @Autowired
    ProductionOrderRepository productionOrderRepository;
    @Autowired
    SimpleMessagePublisher simpleMessagePublisher;
    public com.sap.academy.production.models.ProductionOrderHeader createProductionOrder(com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader){
        com.sap.academy.production.entities.ProductionOrderHeader productionOrderHeader1 = new com.sap.academy.production.entities.ProductionOrderHeader(productionOrderHeader);
        productionOrderRepository.save(productionOrderHeader1);
        productionOrderHeader.setEventId(UUID.randomUUID().toString());
        productionOrderHeader.setEventType(Operation.CREATE.toString());
        simpleMessagePublisher.publish("com.sap.academy.production-"+TechnicalContextHolder.getTechnicalContext().getTenantId(),new Gson().toJson(productionOrderHeader).toString());
        return productionOrderHeader;
    }
    public void deleteProductionOrder(String productionOrderHeaderId){
        productionOrderRepository.deleteById(productionOrderHeaderId);
    }
    public com.sap.academy.production.models.ProductionOrderHeader getById(@PathVariable(required = true) String id) throws Exception {
        com.sap.academy.production.models.ProductionOrderHeader productionOrderHeader = null;
        try {
            Optional<com.sap.academy.production.entities.ProductionOrderHeader> productionOrderHeaderOptional = productionOrderRepository.findById(id);
            com.sap.academy.production.entities.ProductionOrderHeader productionOrderHeaderTemp = productionOrderHeaderOptional.orElseThrow(()->new Exception("Sales order not found"));
            return productionOrderHeaderTemp.toModel();
        } catch (Exception e) {
            throw e;
        }
    }
}