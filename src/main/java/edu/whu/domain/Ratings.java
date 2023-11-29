package edu.whu.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ratings")
public class Ratings {
    private Integer id;
    private Integer postId;
    private Integer petId;
    private Integer star;
}