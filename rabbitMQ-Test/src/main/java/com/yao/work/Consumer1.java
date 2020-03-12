package com.yao.work;


import com.rabbitmq.client.*;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者2
public class Consumer1 {
    //声明队列名字
    private static final String QUEUE_NAME="yao-work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消费者的声明
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2接收到的消息是:"+new String(body));
                //进行手动应答
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //绑定消费者
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
    }
}


