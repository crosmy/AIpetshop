package edu.whu.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "transaction_id", type = IdType.AUTO)
    private Integer transactionId;

    private Integer sellerId;

    private Integer buyerId;

    private Integer petId;

    private BigDecimal price;

    private LocalDate transactionDate;

    private String status;


}
