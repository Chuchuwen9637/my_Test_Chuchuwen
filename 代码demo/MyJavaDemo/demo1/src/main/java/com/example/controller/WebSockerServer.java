package com.example.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
@ServerEndpoint("/ws/{sid}/{uid}")
public class WebSockerServer {
    private static Map<String, Session> sessionMap=new HashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        System.out.println("客户端"+sid+"建立连接");
        sessionMap.put(sid,session);

    }
    @OnMessage
    public void  onMessage(String message,@PathParam("sid") String sid,@PathParam("uid")String uid) throws IOException {
        System.out.println("收i到来自客户端: "+sid+"的信息"+message);
        System.out.println("收i到来自客户端: "+uid+"的信息"+message);
        if (uid!=null){
            //要发给的对象
            sendToByidclient(message,uid);
        }
    }
    public void Onclose(@PathParam("sid")String sid){
        System.out.println("连接断开"+sid);
        sessionMap.remove(sid);
    }
    public void OnClose(@PathParam("sid") String sid){
        System.out.println("连接断开: "+sid);
        sessionMap.remove(sid);
    }
//群发
    public void sendToAllClient(String message) throws IOException {
        Collection<Session> sessions=sessionMap.values();
        for (Session session:sessions){
            session.getBasicRemote().sendText(message);
        }
    }
    //单发
    public static void  sendToByidclient(String messgae,String uid) throws IOException {
//        Collection<Session> sessions=sessionMap.values();
        if (sessionMap.containsKey(uid)){
            Session session=sessionMap.get(uid);
            System.out.println("向uid发送"+uid+":"+messgae);

            session.getBasicRemote().sendText(messgae);
        }
    }
}
