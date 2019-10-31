package com.sap.academy.sales.processor;

import com.sap.academy.RabbitConfigurationProperties;
import com.sap.academy.RabbitConnectionFactoryConfiguration;
import com.sap.academy.consumer.QueueConsumer;
import com.sap.academy.processor.MessageHandler;
import com.sap.academy.processor.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;


@Component
public class ConsumerStarter {
    static int numberOfConsumers = 5;
    static ArrayList<QueueConsumer> consumers;

    private static BlockingQueue queue=new LinkedBlockingQueue(5);
    private static ThreadPoolExecutor executorService = null;

    @Value("${spring.application.name}")
    private String applicationId;

    @Autowired
    private ApplicationContext context;


    @Autowired
    private RabbitConnectionFactoryConfiguration rabbitConnectionFactoryConfiguration;

    @Autowired
    private RabbitConfigurationProperties properties;

    @Autowired
    MessageDelegator processor;


    public void startConsumers(String tenantId) throws IOException, TimeoutException {
        init();
        for (int i = 0; i < numberOfConsumers; i++) {
            QueueConsumer queueConsumer = new QueueConsumer(rabbitConnectionFactoryConfiguration.getConnection(),applicationId+"-"+tenantId,properties);
            MessageDelegator processor = new MessageDelegator();
            queueConsumer.getProcessor().setMessageProcessor(processor);
            executorService.execute(queueConsumer);
            consumers.add(queueConsumer);
        }
    }

    public void init()  {
        consumers = new ArrayList<>();
        executorService = new ThreadPoolExecutor(8,15,100, TimeUnit.MILLISECONDS,queue);
        for (int i = 0; i < numberOfConsumers; i++) {
            QueueConsumer queueConsumer = null;
            try {
                queueConsumer = new QueueConsumer(rabbitConnectionFactoryConfiguration.getConnection(), applicationId + "-" , properties);
                //MessageDelegator processor = new MessageDelegator();
                processor = context.getBean(MessageDelegator.class);
                queueConsumer.getProcessor().setMessageProcessor(processor);
                consumers.add(queueConsumer);
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




    public void bindTenantToConsumer(String tenantId) throws IOException, TimeoutException {
        consumers.forEach(queueConsumer -> {
            try {
                queueConsumer.setAccountId(applicationId+"-"+tenantId);
                queueConsumer.bindRoutingkeyToConsumer(applicationId+"-"+tenantId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }




}
