/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Message object class
 * @author yanma
 */
public class Message implements Serializable{
    private int mid;
    private String sender;
    private String receiver;
    private Timestamp time;
    private String content;
    private int is_read;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }
    
    
}
