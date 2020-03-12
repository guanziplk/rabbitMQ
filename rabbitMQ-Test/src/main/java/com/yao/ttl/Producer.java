package com.yao.ttl;

import com.rabbitmq.client.*;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//生产者
public class Producer {
    //申明队列的名字
    private static final String QUEUE_NAME="yao-ttl";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> map=new HashMap<>();
        map.put("x-message-ttl",5000);
        channel.queueDeclare(QUEUE_NAME,false,false,false,map);
        channel.basicPublish("",QUEUE_NAME, null,("DDDDD").getBytes());
        channel.close();
        connection.close();
    }
}

