package com.yao.helloworld;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消息生产者
public class Producer {
    private static final String QUEUE_NAME="yao-helloworld";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicPublish("",QUEUE_NAME,null,"BBBBB".getBytes());
        channel.close();
        connection.close();

    }
}


