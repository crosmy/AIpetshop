package edu.whu.controller;


import edu.whu.domain.Pet;
import edu.whu.exception.CustomException;
import edu.whu.service.IPetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    /**
     * addPet(@RequestBody Pet pet),
     * updatePet(@RequestBody Pet pet),
     * getPet(int petId),
     * deletePet(int petId),
     * getAllPets().
     */

    @Autowired
    private IPetService petService;

    @ApiOperation(value = "添加宠物",notes = "用来注册宠物")
    @PostMapping("/add")
    public String addPet(@RequestBody Pet pet) throws CustomException {
        petService.addPet(pet);
        return "成功添加宠物" + pet.getName();
    }

    @ApiOperation(value = "删除宠物",notes = "用来删除宠物")
    @DeleteMapping("/delete/{petId}")
    public String deletePet(@PathVariable Integer petId) throws CustomException {
        petService.deletePet(petId);
        return "成功删除宠物";
    }

    @ApiOperation(value = "更新宠物",notes = "用来更新宠物信息")
    @PutMapping("/update")
    public String updatePet(@RequestBody Pet pet) throws CustomException {
        petService.updatePet(pet);
        return "成功更新宠物" + pet.getName();
    }

    @ApiOperation(value = "获取宠物",notes = "用来获取指定宠物的信息")
    @GetMapping("/get/{petId}")
    public Pet getPet(@PathVariable Integer petId) throws CustomException {
        return petService.getPetById(petId);
    }

    @ApiOperation(value = "获取所有宠物",notes = "用来获取所有宠物的信息")
    @GetMapping("/getAll")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

}

