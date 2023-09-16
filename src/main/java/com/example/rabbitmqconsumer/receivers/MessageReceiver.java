package com.example.rabbitmqconsumer.receivers;

import com.example.rabbitmqconsumer.models.MyMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    //----------------------------------------------------------
    // Rabbit Listener for auto or none acknowledgement mode
    //----------------------------------------------------------
    @RabbitListener(
            queues = "#{requestQueue.name}",
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void receiveMessage(MyMessage message) throws Exception {
        System.out.println("Received: "+message.toString());
        Thread.sleep(9000);
        if(message.getMessageId() > 5){
            throw new Exception("abc");
        } else if(message.getMessageId() == 3){
            Thread.sleep(19000);
            System.out.println("Listener method executed successfully");
        } else {
            System.out.println("Listener method executed successfully w/o sleep");
        }
    }

    //-------------------------------------------------
    // Rabbit Listener for manual acknowledgement mode
    //-------------------------------------------------
    //@RabbitListener(
    //        queues = "#{requestQueue.name}",
    //        containerFactory = "rabbitListenerContainerFactory"
    //)
    //public void receiveMessage(MyMessage message, Channel channel,
    //        @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
    //    System.out.println("Received: "+message.toString());
    //    Thread.sleep(9000);
    //    if(message.getMessageId() > 5){
    //        // Error path
    //        channel.basicNack(tag, false, true);
    //    } else if(message.getMessageId() == 3){
    //        Thread.sleep(19000);
    //        System.out.println("Listener method executed successfully");
    //        channel.basicAck(tag, false);
    //    } else {
    //        System.out.println("Listener method executed successfully w/o sleep");
    //        channel.basicAck(tag, false);
    //    }
    //}
}
