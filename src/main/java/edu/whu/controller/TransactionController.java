package edu.whu.controller;


import edu.whu.domain.Transaction;
import edu.whu.exception.CustomException;
import edu.whu.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.Inet4Address;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    /**
     * initiateTransaction(@RequestBody Transaction transaction),
     * updateTransaction(@RequestBody Transaction transaction),
     * getTransaction(int transactionId).
     */

    @Autowired
    private ITransactionService transactionService;

    // 创建交易
    @PostMapping
    public Transaction createTransaction(@RequestParam Integer buyerId, @RequestParam Integer sellerId, @RequestParam BigDecimal amount) throws CustomException {
        return transactionService.createTransaction(buyerId, sellerId, amount);
    }

    // 更新交易状态
    @PutMapping("/{id}/status")
    public void updateTransactionStatus(@PathVariable("id") Integer transactionId, @RequestParam("status") String status) throws CustomException {
        transactionService.updateTransactionStatus(transactionId, status);
    }

    // 获取交易
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Integer transactionId) throws CustomException {
        return transactionService.getTransaction(transactionId);
    }
}

