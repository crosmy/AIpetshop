package edu.whu.dao;

import edu.whu.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Mapper
public interface MessageDao extends BaseMapper<Message> {
    @Select("SELECT * FROM message WHERE (sender_id = #{senderId} AND receiver_id = #{receiverId}) OR (sender_id = #{receiverId} AND receiver_id = #{senderId}) ORDER BY timestamp DESC")
    List<Message> findConversation(@Param("senderId") Integer senderId, @Param("receiverId") Integer receiverId);
}

