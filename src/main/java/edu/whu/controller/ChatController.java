package edu.whu.controller;

import edu.whu.domain.ChatRecord;
import edu.whu.domain.MessageDto;
import edu.whu.exception.CustomException;
import edu.whu.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.whu.exception.CustomException.MESSAGE_LOAD_ERROR;
import static edu.whu.exception.CustomException.REPLY_ERROR;

@RestController
@Api(tags = "智能客服接口")
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @ApiOperation(value = "接收消息并返回回复")
    @PostMapping("/message")
    public String receiveMessage(@RequestBody MessageDto messageDto) throws CustomException {
        try {
            return chatService.chatWithModelAndSave(messageDto);
        } catch (Exception e) {
            throw new CustomException(REPLY_ERROR, "处理消息时出现异常");
        }
    }

    @ApiOperation(value = "根据userId和sellerId按时间顺序获取聊天记录")
    @GetMapping("/record")
    public List<ChatRecord> getChatRecord(@RequestParam Integer userId, @RequestParam Integer sellerId) throws CustomException {
        try {
            return chatService.getChatRecord(userId, sellerId);
        } catch (Exception e) {
            throw new CustomException(MESSAGE_LOAD_ERROR, "获取聊天记录时出现异常");
        }
    }
}

