package com.abing.kafka.beans;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

}