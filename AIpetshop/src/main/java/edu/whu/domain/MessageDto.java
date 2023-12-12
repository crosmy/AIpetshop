package edu.whu.domain;

import lombok.Data;

@Data
public class MessageDto{
    private Integer userId;
    private Integer sellerId;
    private String message;
    private String promptKey = "petStore";
}
