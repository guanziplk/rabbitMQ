package com.yao.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class producer {

    //声明交换机的名字
    private static final String EXCHANGE_NAME="yao-fanout-one";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发送消息到交换机
        for (int i = 0; i <100 ; i++) {
            channel.basicPublish(EXCHANGE_NAME,"",null,("发布订阅模型的值"+i).getBytes());
        }

        //关闭资源
        channel.close();
        connection.close();

    }
}


