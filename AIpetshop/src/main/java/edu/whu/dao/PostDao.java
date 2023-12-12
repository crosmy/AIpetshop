package edu.whu.dao;

import edu.whu.domain.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface PostDao extends BaseMapper<Post> {

    @Select("SELECT * FROM post WHERE user_id = #{userId}")
    List<Post> getPostsByUserId(Integer userId);
}
