package edu.whu.service.impl;

import edu.whu.domain.Transaction;
import edu.whu.dao.TransactionDao;
import edu.whu.service.ITransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, Transaction> implements ITransactionService {

}
