package edu.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import edu.whu.dao.PetDao;
import edu.whu.domain.Pet;
import edu.whu.domain.Transaction;
import edu.whu.dao.TransactionDao;
import edu.whu.exception.CustomException;
import edu.whu.service.ITransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static edu.whu.exception.CustomException.*;

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

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private PetDao petDao;

    // 创建交易
    @Transactional
    public Transaction createTransaction(Transaction transaction) throws CustomException {
        int result = transactionDao.insert(transaction);
        if (result <= 0) {
            throw new CustomException(CREATE_FAILED, "创建交易失败");
        }
        petDao.update(petDao.selectById(transaction.getPetId()), new UpdateWrapper<Pet>().lambda().eq(Pet::getPetId, transaction.getPetId()).set(Pet::getIsSold, true));
        return transaction;
    }

    // 更新交易状态
    @Transactional
    public void updateTransactionStatus(Integer transactionId, String status) throws CustomException {
        Transaction transaction = transactionDao.selectById(transactionId);
        if (transaction == null) {
            throw new CustomException(TRANSACTION_NOT_FOUND, "交易不存在");
        }

        transaction.setStatus(status);
        int result = transactionDao.updateById(transaction);
        if (result <= 0) {
            throw new CustomException(UPDATE_FAILED, "更新交易失败");
        }
    }

    @Override
    public Transaction getTransaction(Integer transactionId) throws CustomException {
        Transaction transaction = transactionDao.selectById(transactionId);
        if (transaction == null) {
            throw new CustomException(TRANSACTION_NOT_FOUND, "交易不存在");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getOrderList(Integer userId) throws CustomException {
        try {
            QueryWrapper<Transaction> wrapper = new QueryWrapper<>();
            wrapper.eq("buyer_id", userId).or().eq("seller_id", userId);
            return transactionDao.selectList(wrapper);
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR, "获取订单列表时出错");
        }
    }
}
