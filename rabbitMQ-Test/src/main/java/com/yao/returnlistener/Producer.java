package com.yao.returnlistener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;
import com.yao.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Producer {
    private static final String EXCHANGE_NAME="yao-exchange-return";
    //是能路由的key
    private static final String ROUTING_SUCCESS_KEY="return.save";
    //是不能路由的key
    private static final String ROUTING_ERROR_KEY="fail.save";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //添加监听
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("监听到不可达的消息");
                System.out.println("状态码："+i+"---文本信息:"+s+"---交换机名字:"+s1+"----路由的key:s2");
                System.out.println("监听到不可达的消息");
                System.out.println("监听到不可达的消息");
                System.out.println("监听到不可达的消息");
            }
        });
        channel.basicPublish(EXCHANGE_NAME,ROUTING_ERROR_KEY,true,null,"这里是测试Return机制".getBytes());

    }



}
