package com.yao.returnlistener;

import com.rabbitmq.client.*;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Consumer {
    private static final String EXCHANGE_NAME="yao-exchange-return";
    //是能路由的key
    private static final String ROUTING_SUCCESS_KEY="return.#";
    //制定绑定的队列
    private static final String QUEUE_NAME="yao-return-queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_SUCCESS_KEY);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到这个消息了....");
            }
        };
        //进行消费的绑定
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);


    }

}
