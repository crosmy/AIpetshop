package edu.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.domain.Pet;
import edu.whu.dao.PetDao;
import edu.whu.domain.Post;
import edu.whu.exception.CustomException;
import edu.whu.service.IPetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static edu.whu.exception.CustomException.QUERY_ERROR;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetDao, Pet> implements IPetService {

    @Autowired
    private PetDao petDao;

    @Override
    public void addPet(Pet pet) throws CustomException {
        if (pet.getName() == null || pet.getPrice() == null || pet.getAge() == null || pet.getDescription() == null || pet.getPhotoUrl() == null) {
            throw new CustomException(CustomException.VALIDATION_ERROR, "宠物的必要属性不能为空");
        }
        petDao.insert(pet);
    }

    @Override
    public void deletePet(Integer petId) throws CustomException {
        Pet pet = petDao.selectById(petId);
        if (pet == null) {
            throw new CustomException(CustomException.PET_NOT_FOUND, "宠物不存在");
        }

        petDao.deleteById(petId);
    }

    @Override
    public void updatePet(Pet pet) throws CustomException {
        Pet existingPet = petDao.selectById(pet.getPetId());
        if (existingPet == null) {
            throw new CustomException(CustomException.PET_NOT_FOUND, "宠物不存在");
        }
        petDao.updateById(pet);
    }

    @Override
    public Pet getPetById(Integer petId) throws CustomException {
        Pet pet = petDao.selectById(petId);
        if (pet == null) {
            throw new CustomException(CustomException.PET_NOT_FOUND, "宠物不存在");
        }
        return petDao.selectById(petId);
    }

    @Override
    public List<Pet> getAllPets() {
        return petDao.selectList(null);
    }

    @Override
    public IPage<Pet> findPets(Map<String, Object> condition, Integer pageNum, Integer pageSize) throws CustomException {
        try {
            QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();

            // 检查是否有关键词搜索
            if (condition.containsKey("keyword")) {
                String keyword = (String) condition.get("keyword");
                queryWrapper.nested(i -> i
                        .like("title", keyword)
                        .or()
                        .like("content", keyword));
            }
            // 构建条件
            queryWrapper.ge(condition.containsKey("minPrice"), "price", condition.get("minPrice"))
                    .le(condition.containsKey("maxPrice"), "price", condition.get("maxPrice"))
                    .eq(condition.containsKey("type"), "type", condition.get("type"));

            // 添加排序条件
            if (condition.containsKey("orderField") && condition.containsKey("orderType")) {
                boolean isAsc = "asc".equalsIgnoreCase((String)condition.get("orderType"));
                queryWrapper.orderBy(true, isAsc, (String) condition.get("orderField"));
            }

            // 执行分页查询
            Page<Pet> page = new Page<>(pageNum, pageSize);
            return petDao.selectPage(page, queryWrapper);

        } catch (Exception e) {
            // 在遇到异常时抛出 CustomException
            throw new CustomException(QUERY_ERROR, "在查询宠物时出现异常: " + e.getMessage());
        }
    }

    @Override
    public List<Pet> getPetsByOwnerId(Integer ownerId) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("owner_id", ownerId);
        return petDao.selectList(queryWrapper);
    }

    @Override
    public List<Pet> searchPet(String petName) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", petName);
        return petDao.selectList(queryWrapper);
    }

    @Override
    public Pet searchById(Integer petId) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pet_id", petId);
        return petDao.selectOne(queryWrapper);
    }
}
