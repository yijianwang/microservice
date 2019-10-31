package com.sap.academy.sales.service;

import com.sap.academy.RabbitConfigurationProperties;
import com.sap.academy.RabbitConnectionFactoryConfiguration;
import com.sap.academy.consumer.QueueConsumer;
import com.sap.academy.dbhandler.DynamicDeployApi;
import com.sap.academy.publish.SimpleMessagePublisher;
import com.sap.academy.sales.processor.ConsumerStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;


@Component
public class DbDeployer {

    @Autowired
    ConsumerStarter consumerStarter;

    @Autowired
    private DynamicDeployApi dynamicDeployApiImpl;

    @Value("${spring.application.name}")
    private String applicationId;

    public void prepareExistingTenants(){
        consumerStarter.init();
        List<String> tenants = dynamicDeployApiImpl.getAllTenants(applicationId);
        tenants.forEach(tenantId->{
            try {
                prepareTenant(tenantId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void prepareTenant(String tenantId) throws Exception {
        dynamicDeployApiImpl.redeployDBModule(tenantId);
        consumerStarter.bindTenantToConsumer(tenantId);
    }

    public void prepareNewTenant(String tenantId,String databaseId) throws Exception {
        dynamicDeployApiImpl.deployDBModule(databaseId,tenantId);
        consumerStarter.bindTenantToConsumer(tenantId);
    }

}
