package edu.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.dao.RatingsDao;
import edu.whu.domain.Post;
import edu.whu.dao.PostDao;
import edu.whu.exception.CustomException;
import edu.whu.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.service.IRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static edu.whu.exception.CustomException.*;

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

    @Transactional
    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postDao.selectList(null);
        return posts;
    }

    @Override
    public IPage<Post> findPosts(Map<String, Object> condition, Integer pageNum, Integer pageSize) throws CustomException {
        try {
            QueryWrapper<Post> queryWrapper = new QueryWrapper<>();

            // 检查是否有关键词搜索
            if (condition.containsKey("keyword")) {
                String keyword = (String) condition.get("keyword");
                queryWrapper.nested(i -> i
                        .like("title", keyword)
                        .or()
                        .like("content", keyword)
                        .or()
                        .like("username", keyword)
                        .or()
                        .like("user_id", keyword));
            }
            // 构建条件
            queryWrapper.eq(condition.containsKey("type"), "type", condition.get("type"));
            queryWrapper.eq(condition.containsKey("username"), "username", condition.get("username"));
            queryWrapper.eq(condition.containsKey("userId"), "user_id", condition.get("userId"));

            if (condition.containsKey("petId")) {
                String petId = (String) condition.get("petId");
                queryWrapper.apply("JSON_CONTAINS(petIds, {0})", petId);
            }

            if (condition.containsKey("petName")) {
                String petName = (String) condition.get("petName");
                queryWrapper.apply("JSON_CONTAINS(petNames, {0})", petName);
            }

            // 添加排序条件
            if (condition.containsKey("orderField") && condition.containsKey("orderType")) {
                boolean isAsc = "asc".equalsIgnoreCase((String)condition.get("orderType"));
                queryWrapper.orderBy(true, isAsc, toColumnName((String) condition.get("orderField")));
            }

            // 执行分页查询
            Page<Post> page = new Page<>(pageNum, pageSize);
            return postDao.selectPage(page, queryWrapper);

        } catch (Exception e) {
            // 在遇到异常时抛出 CustomException
            throw new CustomException(QUERY_ERROR, "在查询帖子时出现异常: " + e.getMessage());
        }
    }

    @Override
    public Double getAverageRating(Integer postId) throws CustomException {
        if (postId == null) {
            throw new CustomException(CustomException.VALIDATION_ERROR, "postId 不为空");
        }

        try {
            return postDao.selectById(postId).getStars();
        } catch (Exception e) {
            throw new CustomException(CustomException.DATABASE_ERROR, "求评分异常 " + e.getMessage());
        }
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) throws CustomException {
        try {
            QueryWrapper<Post> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            return postDao.selectList(wrapper);
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR, "获取用户帖子列表时出错");
        }
    }

    /**
     * 将字段名转换为数据库列名
     * @param fieldName
     * @return
     */
    private String toColumnName(String fieldName) {
        StringBuilder columnName = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                columnName.append("_");
            }
            columnName.append(Character.toLowerCase(c));
        }
        return columnName.toString();
    }


}
