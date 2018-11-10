package com.ml.sell.service;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author
 * @date 2018/11/10
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> writeArraySet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        writeArraySet.add(this);
        log.info("[WebSocket消息] 有新的连接，总数：{}", writeArraySet.size());
    }

    @OnClose
    public void onClose() {
        writeArraySet.remove(this);
        log.info("[WebSocket消息] 连接断开，总数：{}", writeArraySet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[WebSocket消息] 收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : writeArraySet) {
            log.info("[WebSocket消息] 广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("[WebSocket消息] 广播消息异常：{}", e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocket webSocket = (WebSocket) o;
        return Objects.equal(session, webSocket.session);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(session);
    }

}
