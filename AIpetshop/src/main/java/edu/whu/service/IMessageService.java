package edu.whu.service;

import edu.whu.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.exception.CustomException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IMessageService extends IService<Message> {
    Message sendMessage(Integer senderId, Integer receiverId, String content) throws CustomException;

    List<Message> getConversation(Integer senderId, Integer receiverId) throws CustomException;
    /**
     * Functions: sendMessage(Message message), getMessagesBetweenUsers(int senderId, int receiverId).
     */
}
