package edu.whu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.domain.Pet;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.domain.Post;
import edu.whu.exception.CustomException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IPetService extends IService<Pet> {
    void addPet(Pet pet) throws CustomException;

    void deletePet(Integer petId) throws CustomException;

    void updatePet(Pet pet) throws CustomException;

    Pet getPetById(Integer petId) throws CustomException;

    List<Pet> getAllPets();

    IPage<Pet> findPets(Map<String, Object> condition, Integer pageNum, Integer pageSize) throws CustomException;
    /**
     * Functions: addPet(Pet pet), updatePet(Pet pet), getPetById(int petId), deletePet(int petId), getAllPets().
     */
}
