package edu.whu.service.impl;

import edu.whu.domain.Post;
import edu.whu.dao.PostDao;
import edu.whu.exception.CustomException;
import edu.whu.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.whu.exception.CustomException.POST_NOT_COMPLETE;
import static edu.whu.exception.CustomException.POST_NOT_FOUND;

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

    @Autowired
    private PostDao postDao;

    @Transactional
    @Override
    public void createPost(Post post) throws CustomException {
        //
        if (post.getTitle() == null || post.getContent() == null) {
            throw new CustomException(POST_NOT_COMPLETE, "帖子ID不为空");
        }
        postDao.insert(post);
    }

    @Transactional
    @Override
    public Post getPostById(Integer postId) throws CustomException {
        Post post = postDao.selectById(postId);
        if (post == null) {
            throw new CustomException(POST_NOT_FOUND, "帖子不存在");
        }

        return post;
    }

    @Transactional
    @Override
    public void updatePost(Post updatedpost) throws CustomException {

        // 判断：确保更新的帖子存在
        Post existingPost = postDao.selectById(updatedpost.getPostId());
        if (existingPost == null) {
            throw new CustomException(POST_NOT_FOUND, "ID为 " + updatedpost.getPostId() + " 的帖子不存在");
        }
        postDao.updateById(updatedpost);

    }

    @Transactional
    @Override
    public void deletePost(Integer postId) throws CustomException {

        // 判断：确保要删除的帖子存在
        if (postDao.selectById(postId) == null) {
            throw new CustomException(POST_NOT_FOUND, "ID为 " + postId + " 的帖子不存在");
        }
        postDao.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postDao.selectList(null);
        return posts;
    }
}
