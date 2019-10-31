package com.sap.academy.sales.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
    @Autowired
    private DbDeployer dbDeployer;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationReadyEvent) {
        dbDeployer.prepareExistingTenants();
    }
}
