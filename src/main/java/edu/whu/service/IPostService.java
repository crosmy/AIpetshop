package edu.whu.service;

import edu.whu.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.exception.CustomException;
import javafx.geometry.Pos;

import java.util.List;

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
    /**
     * Functions: createPost(Post post), updatePost(Post post), getPostById(int postId), deletePost(int postId), getAllPosts().
     */
}
