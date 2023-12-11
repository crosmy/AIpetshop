package edu.whu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.domain.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRecordDao extends BaseMapper<ChatRecord> {

}

