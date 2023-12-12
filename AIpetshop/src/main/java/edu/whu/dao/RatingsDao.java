package edu.whu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.domain.Ratings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RatingsDao extends BaseMapper<Ratings> {
    @Insert("INSERT INTO ratings (post_id, user_id, star) VALUES (#{postId}, #{userId}, #{star})")
    void insertPostRating(Integer postId, Integer userId, Integer star);

    @Update("UPDATE ratings SET star = #{star} WHERE post_id = #{postId} AND user_id = #{userId}")
    void updatePostRating(Integer postId, Integer userId, Integer star);

    @Update("UPDATE post SET stars = (SELECT AVG(star) FROM ratings WHERE post_id = #{postId}) WHERE post_id = #{postId}")
    void updatePostStars(Integer postId);
}
