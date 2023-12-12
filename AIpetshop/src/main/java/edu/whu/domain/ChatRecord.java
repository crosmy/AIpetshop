package edu.whu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class ChatRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userMessage;
    private String modelResponse;
    private Date timestamp;

    private Integer userId;
    private Integer sellerId;

}

