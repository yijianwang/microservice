package com.sap.academy.sales.processor;

import com.sap.academy.dbhandler.TechnicalContextHolder;
import com.sap.academy.processor.MessageProcessor;
import com.sap.academy.utils.HDIConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class MessageDelegator implements MessageProcessor {
    String message;
    Map<String,Object> header;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void processMessage() {
        logger.info("Incoming meessage==>{}",this.message);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setMessage(String message, Map<String, Object> header) {
        this.message = message;
        this.header = header;
        setTechnicalContext();
    }

    private void setTechnicalContext(){
        logger.info("tenantid===>"+header.get("tenantId").toString());
        if(TechnicalContextHolder.getTechnicalContext()==null)
            TechnicalContextHolder.init();
        TechnicalContextHolder.getTechnicalContext().setTenantId(header.get("tenantId").toString());
    }

    @Override
    public void run() {
        processMessage();
    }
}
