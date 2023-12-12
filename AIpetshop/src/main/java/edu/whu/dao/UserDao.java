package edu.whu.dao;

import edu.whu.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("update user set profile_picture_url = #{photo} where username = #{username}")
    void updateUserPhoto(String username, String photo);
}
