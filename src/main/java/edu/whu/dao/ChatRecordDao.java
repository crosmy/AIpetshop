package edu.whu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.domain.ChatRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatRecordDao extends BaseMapper<ChatRecord> {

    @Select("SELECT * FROM chat_record WHERE user_id = #{userId} AND seller_id = #{sellerId} ORDER BY timestamp DESC")
    List<ChatRecord> getChatRecords(Integer userId, Integer sellerId);
}

