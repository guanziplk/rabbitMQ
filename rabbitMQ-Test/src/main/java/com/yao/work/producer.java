package com.yao.work;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


//生产者
public class producer {
    private static final String QUEUE_NAME="yao-work";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //向队列中发送消息
        for (int i = 0; i <100 ; i++) {
            channel.basicPublish("",QUEUE_NAME,null,("这里是work"+i).getBytes());
        }

        channel.close();
        connection.close();

    }
}


