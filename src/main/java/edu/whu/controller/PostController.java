package edu.whu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.domain.Post;
import edu.whu.exception.CustomException;
import edu.whu.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */

@Api(tags = "帖子相关接口")
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


    @ApiOperation(value = "创建帖子",notes = "创建帖子")
    @PostMapping("/create")
    public String createPost(@RequestBody Post post) throws CustomException {
        postService.createPost(post);
        return "成功创建帖子";
    }

    @ApiOperation(value = "根据帖子id获取帖子",notes = "根据帖子id获取帖子")
    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Integer postId) throws CustomException {
        return postService.getPostById(postId);
    }

    @ApiOperation(value = "更新帖子",notes = "更新帖子")
    @PutMapping("/{postId}")
    public String updatePost(@PathVariable Integer postId, @RequestBody Post updatedpost) throws CustomException {
        updatedpost.setPostId(postId);
        postService.updatePost(updatedpost);
        return "成功更新帖子";
    }

    @ApiOperation(value = "删除帖子",notes = "删除帖子")
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Integer postId) throws CustomException {
        postService.deletePost(postId);
        return "成功删除帖子";
    }

    @ApiOperation(value = "获取所有帖子",notes = "获取所有帖子")
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @ApiOperation(value = "根据关键字查询帖子",notes = "根据关键字查询帖子")
    @GetMapping("/query")
    public IPage<Post> findPost(@ApiParam("帖子关键字")@RequestParam(value = "keyword",required = false) String keyword,
                                @ApiParam("宠物最低价格") @RequestParam(value = "minPrice",required = false)  Integer minPrice,
                                @ApiParam("宠物最高价格") @RequestParam(value = "maxPrice",required = false)  Integer maxPrice,
                                @ApiParam("排序字段") @RequestParam(value = "orderField",required = false)  String orderField,
                                @ApiParam("排序方式") @RequestParam(value = "orderType",required = false)  String orderType,
                                @ApiParam("宠物类别") @RequestParam(value = "type",required = false)  String type,
                                @ApiParam("页码")@RequestParam(defaultValue = "0")Integer pageNum,
                                @ApiParam("每页记录数") @RequestParam(defaultValue = "10")Integer pageSize) throws CustomException {
        Map<String,Object> condition=new HashMap<>();
        if(keyword!=null) {
            condition.put("keyword",keyword);
        }
        if(maxPrice!=null) {
            condition.put("maxPrice",maxPrice);
        }
        if(minPrice!=null) {
            condition.put("minPrice",minPrice);
        }
        if(type!=null) {
            condition.put("type",type);
        }
        //判断是否需要排序，排序的方式只有三种：点赞量，销量和评分
        if(orderField!=null&&(orderField.equals("stars"))){
            condition.put("orderField",orderField);
        }
        //判断排序的类型
        if(orderType!=null){
            condition.put("orderType",orderType);
        }
        return postService.findPosts(condition, pageNum, pageSize);
    }



}

