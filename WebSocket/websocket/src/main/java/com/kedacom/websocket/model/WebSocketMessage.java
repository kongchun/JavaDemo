/**
 * 
 */
package com.kedacom.websocket.model;

/**
 * @author nexus
 *
 */
public class WebSocketMessage {

    private String from;
    private String to;
    private String message;

    /**
     * @return the from
     */
    public String getFrom() {
	return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
	this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
	return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
	this.to = to;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

}
