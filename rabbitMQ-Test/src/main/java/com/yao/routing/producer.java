package com.yao.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


//生产者
public class producer {
    //声明交换机名字
    private static final String EXCHANGE_NAME="yao-exchange-direct-01";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        for (int i = 0; i < 100; i++) {
            if (i%2==0){
                //发送信息到交换机
                channel.basicPublish(EXCHANGE_NAME,"yao",null,("路由模型为:"+i).getBytes());
            }else {
                channel.basicPublish(EXCHANGE_NAME,"yaoziyi",null,("路由模型为:"+i).getBytes());
            }
        }
        //关闭资源
        channel.close();
        connection.close();
    }
}


