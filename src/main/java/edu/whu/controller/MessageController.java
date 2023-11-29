package edu.whu.controller;


import edu.whu.domain.Message;
import edu.whu.domain.MessageDto;
import edu.whu.exception.CustomException;
import edu.whu.service.IMessageService;
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
    @PostMapping("/send")
    public Message sendMessage(@RequestBody MessageDto messageDto) throws CustomException {
        return messageService.sendMessage(messageDto.getSenderId(), messageDto.getReceiverId(), messageDto.getContent());
    }

    // 获取对话历史
    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam Integer senderId, @RequestParam Integer receiverId) throws CustomException {
        return messageService.getConversation(senderId, receiverId);
    }
}

