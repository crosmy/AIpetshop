package edu.whu.service;

import edu.whu.domain.Transaction;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.exception.CustomException;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface ITransactionService extends IService<Transaction> {
    Transaction createTransaction(Integer buyerId, Integer sellerId) throws CustomException;

    void updateTransactionStatus(Integer transactionId, String status) throws CustomException;

    Transaction getTransaction(Integer transactionId) throws CustomException;

    List<Transaction> getOrderList(Integer userId) throws CustomException;
}
