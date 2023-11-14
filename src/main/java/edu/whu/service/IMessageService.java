package edu.whu.service;

import edu.whu.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IMessageService extends IService<Message> {
    /**
     * Functions: sendMessage(Message message), getMessagesBetweenUsers(int senderId, int receiverId).
     */
}
