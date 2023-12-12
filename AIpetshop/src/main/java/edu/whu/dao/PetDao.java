package edu.whu.dao;

import edu.whu.domain.Pet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface PetDao extends BaseMapper<Pet> {

    @Select("SELECT * FROM pet WHERE owner_id = #{userId}")
    List<Pet> getPetsByUserId(Integer userId);
}
