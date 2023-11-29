package edu.whu.dao;

import edu.whu.domain.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface PostDao extends BaseMapper<Post> {

    @Update("UPDATE post SET stars = #{star} WHERE post_id = #{id}")
    void updatePostRating(Long id, Integer star);
}
