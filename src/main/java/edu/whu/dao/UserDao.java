package edu.whu.dao;

import edu.whu.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where username =  #{username}")
    User getUser(String username);
}
