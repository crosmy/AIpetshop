package edu.whu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pet_id", type = IdType.AUTO)
    private Integer petId;

    private Integer ownerId;

    private String name;

    private String type;

    private String breed;

    private Integer age;

    private String description;

    private String healthInformation;

    private String vaccinations;

    private Double price;

    private String photoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //宠物是否售出
    private Boolean isSold = false;


}
