package com.sap.academy.production.controller;


import com.google.gson.Gson;
import com.sap.academy.dbhandler.TechnicalContext;
import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.production.DbDeployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/onboard")
public class OnboardingController {

    String tenantId,databaseId,userId;

    @Autowired
    DbDeployer dbDeployer;

    @PostMapping("/tenant")
    public ResponseEntity<String> tenantOnboard(@RequestHeader Map<String, String> headers, @RequestBody Map<String, String> tenantParameters) {
        Gson gson = new Gson();
        tenantId = tenantParameters.get("tenantId");
        databaseId = tenantParameters.get("databaseId");
        userId = tenantParameters.get("userId");
        TechnicalContext technicalContext = new TechnicalContext();
        technicalContext.setTenantId(tenantId);
        technicalContext.setUserId(userId);
        TechnicalContextHolder.setTechnicalContext(technicalContext);
        try {
            dbDeployer.prepareNewTenant(tenantId,databaseId);
            return new ResponseEntity<String>(gson.toJson(tenantParameters).toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson(tenantParameters).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
