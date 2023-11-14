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
    private Integer transaction_id;

    private Integer seller_id;

    private Integer buyer_id;

    private Integer pet_id;

    private BigDecimal price;

    private LocalDate transaction_date;

    private String status;


}
