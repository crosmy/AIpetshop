package edu.whu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.exception.CustomException;
import javafx.geometry.Pos;

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
public interface IPostService extends IService<Post> {
    void createPost(Post post) throws CustomException;

    Post getPostById(Integer postId) throws CustomException;

    void updatePost(Post updatedpost) throws CustomException;

    void deletePost(Integer postId) throws CustomException;

    List<Post> getAllPosts();

    IPage<Post> findPosts(Map<String, Object> condition, Integer pageNum, Integer pageSize) throws CustomException;
    /**
     * Functions: createPost(Post post), updatePost(Post post), getPostById(int postId), deletePost(int postId), getAllPosts().
     */
}
