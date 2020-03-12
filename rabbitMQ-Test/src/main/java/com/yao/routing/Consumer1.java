package com.yao.routing;

import com.rabbitmq.client.*;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者1
public class Consumer1 {
    private static final String QUEUE_NAME="yao-queue-direct-01";
    private static final String EXCHANGE_NAME="yao-exchange-direct-01";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //绑定队列，交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"yao");

        //声明消费者
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由yao收到数据:"+new String(body));
            }
        };
        //绑定消费者
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }
}


