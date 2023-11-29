package edu.whu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.domain.Ratings;
import org.apache.ibatis.annotations.Select;

public interface RatingsDao extends BaseMapper<Ratings> {
    @Select("SELECT AVG(star) FROM ratings WHERE pet_id = #{petId}")
    Double getAverageRatingByPetId(Long petId);
}
