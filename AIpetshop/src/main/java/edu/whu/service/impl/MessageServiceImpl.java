package edu.whu.service.impl;

import edu.whu.domain.Message;
import edu.whu.dao.MessageDao;
import edu.whu.exception.CustomException;
import edu.whu.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static edu.whu.exception.CustomException.NO_CONVERSATION_FOUND;
import static edu.whu.exception.CustomException.SEND_FAILED;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements IMessageService {
    @Autowired
    private MessageDao messageDao;

    // 发送消息
    @Transactional
    public Message sendMessage(Integer senderId, Integer receiverId, String content) throws CustomException {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        int result = messageDao.insert(message);
        if (result <= 0) {
            throw new CustomException(SEND_FAILED, "发送失败");
        }
        return message;
    }

    // 获取对话历史
    @Transactional
    public List<Message> getConversation(Integer senderId, Integer receiverId) throws CustomException {
        List<Message> messages = messageDao.findConversation(senderId, receiverId);
        if (messages == null || messages.isEmpty()) {
            throw new CustomException(NO_CONVERSATION_FOUND, "没有对话历史");
        }
        return messages;
    }
}
