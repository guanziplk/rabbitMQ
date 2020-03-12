package com.yao.publish;

import com.rabbitmq.client.*;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {

    //声明交换机的名字
    private static final String EXCHANGE_NAME="yao-fanout-one";
    //声明队列的名字
    private static final String QUEUE_NAME="yao-fanout-queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通到
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //将队列绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //声明消费者
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("队列2收到的消息是:"+new String(body));
            }
        };

        //绑定消费者
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }

}


