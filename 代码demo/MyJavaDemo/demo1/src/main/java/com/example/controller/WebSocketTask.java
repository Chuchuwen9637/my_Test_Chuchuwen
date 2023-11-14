package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class WebSocketTask {

    @Autowired
    private  WebSockerServer webSockerServer;
    @Scheduled (cron = "* * * * * ?")
    public void sendMessageToClient() throws IOException {
//      webSockerServer.sendToAllClient("这是服务端发送的内容"+new Date().toString());
    }
}
