package edu.whu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import edu.whu.handler.JsonStringTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    private Integer userId;

    private String title;

    private String content;

    @TableField(typeHandler = JsonStringTypeHandler.class)
    private List<String> imageUrls;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableField(typeHandler = JsonStringTypeHandler.class)
    private List<String> petIds;

    private Double stars;

}
