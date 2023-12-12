package edu.whu.controller;


import edu.whu.domain.Message;
import edu.whu.domain.MessageDto;
import edu.whu.exception.CustomException;
import edu.whu.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */

@Api(tags = "消息相关接口")
@RestController
@RequestMapping("/message")
public class MessageController {
    /**
     * sendMessage(@RequestBody Message message),
     * getMessages(int senderId, int receiverId).
     */

    @Autowired
    private IMessageService messageService;

    // 发送消息
    @ApiOperation(value = "发送消息", notes = "参数：senderId, receiverId, content")
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) throws CustomException {
        return messageService.sendMessage(message.getSenderId(), message.getReceiverId(), message.getContent());
    }

    // 获取对话历史
    @ApiOperation(value = "获取对话历史", notes = "参数：senderId, receiverId")
    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam Integer senderId, @RequestParam Integer receiverId) throws CustomException {
        return messageService.getConversation(senderId, receiverId);
    }
}

