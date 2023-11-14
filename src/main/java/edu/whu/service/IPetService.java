package edu.whu.service;

import edu.whu.domain.Pet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IPetService extends IService<Pet> {
    /**
     * Functions: addPet(Pet pet), updatePet(Pet pet), getPetById(int petId), deletePet(int petId), getAllPets().
     */
}
