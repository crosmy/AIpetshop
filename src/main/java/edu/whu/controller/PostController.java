package edu.whu.controller;


import edu.whu.domain.Post;
import edu.whu.exception.CustomException;
import edu.whu.service.IPostService;
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
@RequestMapping("/posts")
public class PostController {
    /**
     * createPost(@RequestBody Post post),
     * updatePost(@RequestBody Post post),
     * getPost(int postId),
     * deletePost(int postId),
     * getAllPosts().
     */

    @Autowired
    private IPostService postService;


    @PostMapping("/create")
    public String createPost(@RequestBody Post post) throws CustomException {
        postService.createPost(post);
        return "成功创建帖子";
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Integer postId) throws CustomException {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public String updatePost(@PathVariable Integer postId, @RequestBody Post updatedpost) throws CustomException {
        updatedpost.setPostId(postId);
        postService.updatePost(updatedpost);
        return "成功更新帖子";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Integer postId) throws CustomException {
        postService.deletePost(postId);
        return "成功删除帖子";
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }


}

