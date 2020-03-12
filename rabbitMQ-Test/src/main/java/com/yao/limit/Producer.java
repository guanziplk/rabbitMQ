package com.yao.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {


    private static final String QUEUE_NAME="yao-limit";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i <100 ; i++) {
            channel.basicPublish("",QUEUE_NAME, null,("CCCCC"+i).getBytes());
        }
        channel.close();
        connection.close();
    }





}
