package com.clan.bean.socket;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by robot on 2017/11/29.
 */
public class ChatMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private Long mesId;
    /**
     * 发起者ID
     */
    private Long sendId;
    /**
     * 接受者ID
     */
    private Long receiveId;
    /**
     * 会话ID
     */
    private String chatId;
    /**
     * 文本
     */
    private String text;
    /**
     * 图片
     */
    private String image;
    /**
     * 声音
     */
    private String audio;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 时间
     */
    private Timestamp sendTme;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMesId() {
        return mesId;
    }

    public void setMesId(Long mesId) {
        this.mesId = mesId;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getSendTme() {
        return sendTme;
    }

    public void setSendTme(Timestamp sendTme) {
        this.sendTme = sendTme;
    }

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }

}
