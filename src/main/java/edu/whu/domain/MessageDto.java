package edu.whu.domain;

import lombok.Data;

@Data
public class MessageDto extends Message{
    private Integer senderId;
    private Integer receiverId;
    private String content;
}
