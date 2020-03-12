package com.yao.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;




public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        //声明连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接的主机
        connectionFactory.setHost("192.168.199.228");
        //设置虚拟机 一般设置成/
        connectionFactory.setVirtualHost("/");
        //设置访问用户名
        connectionFactory.setUsername("root");
        //设置密码
        connectionFactory.setPassword("123456");
        //设置请求端口
        connectionFactory.setPort(5672);
        //创建连接
        return connectionFactory.newConnection();

    }
}


