package edu.whu.service.impl;

import edu.whu.domain.Pet;
import edu.whu.dao.PetDao;
import edu.whu.service.IPetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
