package edu.whu.service;

import edu.whu.domain.Transaction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface ITransactionService extends IService<Transaction> {
    /**
     * Functions: initiateTransaction(Transaction transaction), updateTransaction(Transaction transaction),
     * getTransactionById(int transactionId).
     */
}
