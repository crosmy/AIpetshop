package edu.whu.service.impl;

import edu.whu.domain.Post;
import edu.whu.dao.PostDao;
import edu.whu.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements IPostService {

}
