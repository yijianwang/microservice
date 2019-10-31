package com.sap.academy.sales.service.controller;

import com.google.gson.Gson;
import com.sap.academy.dbhandler.TechnicalContext;
import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.sales.api.models.SalesOrderItem;
import com.sap.academy.sales.api.models.SalesOrderHeader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/salesorder")
public class SalesOrderController {

    @Autowired
    SalesOrderService salesOrderService;

    @PostMapping("/createorder")
    public ResponseEntity<String> newSalesOrder(@RequestHeader(value="tenantId") String tenantId,@RequestHeader(value="userId") String userId, @RequestBody SalesOrderHeader salesOrderHeader) {
        setTechnicalContext(tenantId,userId);
        SalesOrderHeader salesOrderHeader1 = salesOrderService.createSalesOrder(salesOrderHeader);
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(salesOrderHeader1).toString(),HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@RequestHeader(value="tenantId") String tenantId,@RequestHeader(value="userId") String userId,@PathVariable(required = true) String id)  {
        SalesOrderHeader salesOrderHeader = null;
        try {
            setTechnicalContext(tenantId,userId);
            salesOrderHeader = salesOrderService.getById(id);
             Gson gson = new Gson();
            return new ResponseEntity<String>(gson.toJson(salesOrderHeader).toString(),HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/deleteorder/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@RequestHeader(value="tenantId") String tenantId,@RequestHeader(value="userId") String userId,@PathVariable(required = true) String id)  {
        SalesOrderHeader salesOrderHeader = null;
        try {
            setTechnicalContext(tenantId,userId);
            salesOrderService.deleteSalesOrder(id);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
        }

    }

    private void setTechnicalContext(String tenantId,String userId){
        TechnicalContext technicalContext = new TechnicalContext();
        technicalContext.setTenantId(tenantId);
        technicalContext.setUserId(userId);
        TechnicalContextHolder.setTechnicalContext(technicalContext);
    }

}
