/**
 * 
 */
package com.kedacom.websocket.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedacom.websocket.model.WebSocketMessage;

/**
 * 原生WebSocket
 * 
 * @author nexus
 *
 */
@ServerEndpoint(value = "/websocket/{user}")
@Component
public class WebSocketComponent {
    // 连接数统计
    private static int count = 0;
    private String user;
    private Session session;
    private ObjectMapper objectMapper = new ObjectMapper();
    // concurrent包的线程安全Set 用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketComponent> webSocketSet = new CopyOnWriteArraySet<WebSocketComponent>();
    private final Logger logger = LogManager.getLogger(WebSocketComponent.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) {
	this.session = session;
	this.user = user;
	webSocketSet.add(this);
	incCount();
	logger.info("User (" + user + ") signed in, online count: " + getCount());
    }

    @OnClose
    public void onClose(@PathParam("user") String user) {
	webSocketSet.remove(this);
	decCount();
	logger.info("User (" + user + ") signed out, online count: " + getCount());
    }

    @OnError
    public void onError(Throwable error) {
	logger.error(error.getMessage());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
	try {
	    WebSocketMessage webSocketMessage = objectMapper.readValue(message, WebSocketMessage.class);
	    String to = webSocketMessage.getTo();
	    String content = webSocketMessage.getMessage();
	    logger.info(this.user + " to " + to + ": " + content);

	    if (to.equals("")) {
		for (WebSocketComponent item : webSocketSet) {
		    if (item == this) {
			continue;
		    }
		    sendMessage(item.session, message);
		}
	    } else {
		for (WebSocketComponent item : webSocketSet) {
		    if (to.equals(item.user)) {
			sendMessage(item.session, message);
			break;
		    }
		}
	    }
	} catch (IOException e) {
	    logger.warn(e.getMessage());
	}
    }

    private static int getCount() {
	return count;
    }

    private static synchronized void incCount() {
	WebSocketComponent.count++;
    }

    private static synchronized void decCount() {
	WebSocketComponent.count--;
    }

    private void sendMessage(Session session, String message) throws IOException {
	if (null != session) {
	    session.getBasicRemote().sendText(message);
	}
    }
}
