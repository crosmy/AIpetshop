package edu.whu.controller;


import edu.whu.domain.Transaction;
import edu.whu.exception.CustomException;
import edu.whu.service.ITransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Api(tags = "交易相关接口")
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
    @ApiOperation(value = "创建交易", notes = "参数：buyerId, sellerId, amount")
    @PostMapping
    public Transaction createTransaction(@RequestParam Integer buyerId, @RequestParam Integer sellerId, @RequestParam BigDecimal amount) throws CustomException {
        return transactionService.createTransaction(buyerId, sellerId, amount);
    }

    // 更新交易状态
    @ApiOperation(value = "更新交易状态", notes = "参数：transactionId, status")
    @PutMapping("/{id}/status")
    public void updateTransactionStatus(@PathVariable("id") Integer transactionId, @RequestParam("status") String status) throws CustomException {
        transactionService.updateTransactionStatus(transactionId, status);
    }

    // 获取交易
    @ApiOperation(value = "获取交易", notes = "参数：transactionId")
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable("id") Integer transactionId) throws CustomException {
        return transactionService.getTransaction(transactionId);
    }

    @ApiOperation(value = "获取用户的订单列表", notes = "参数：userId")
    @GetMapping("/order/list")
    public List<Transaction> getOrderList(@RequestParam Integer userId) throws CustomException {
        return transactionService.getOrderList(userId);
    }

}

