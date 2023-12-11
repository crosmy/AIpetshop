package edu.whu.controller;

import edu.whu.domain.MessageDto;
import edu.whu.exception.CustomException;
import edu.whu.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import static edu.whu.exception.CustomException.REPLY_ERROR;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public String receiveMessage(@RequestBody MessageDto messageDto) throws CustomException {
        try {
            return chatService.chatWithModelAndSave(messageDto);
        } catch (Exception e) {
            throw new CustomException(REPLY_ERROR, "处理消息时出现异常");
        }
    }
}

