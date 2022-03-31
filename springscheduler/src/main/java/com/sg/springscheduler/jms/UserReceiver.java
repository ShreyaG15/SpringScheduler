//package com.sg.springscheduler.jms;
//
//import com.sg.springscheduler.entity.Customer;
//import org.apache.logging.log4j.message.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class UserReceiver {
//
//
//
//    private Logger logger = LoggerFactory.getLogger(UserReceiver.class);
//    private static AtomicInteger id = new AtomicInteger();
//
//
//
//    public void receiveMessage(Customer receivedCustomer, Message message){
//        logger.info(" >> Original received message: " + message);
//        logger.info(" >> Received user: " + receivedCustomer);
//
//    }
//
//}
