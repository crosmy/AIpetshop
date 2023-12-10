package edu.whu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.domain.Favorite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteDao extends BaseMapper<Favorite> {
    @Insert("INSERT INTO favorites (user_id, post_id) VALUES (#{userId}, #{postId})")
    void insertFavorite(Integer userId, Integer postId);

    @Select("SELECT COUNT(*) FROM favorites WHERE user_id = #{userId} AND post_id = #{postId}")
    int countFavorites(Integer userId, Integer postId);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND post_id = #{postId}")
    void deleteFavorite(Integer userId, Integer postId);

    @Select("SELECT * FROM favorites WHERE user_id = #{userId}")
    List<Favorite> getFavoritesByUserId(Integer userId);
}
