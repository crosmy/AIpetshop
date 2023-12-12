package edu.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.dao.RatingsDao;
import edu.whu.domain.Ratings;
import edu.whu.exception.CustomException;
import edu.whu.service.IRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsDao, Ratings> implements IRatingsService {

    @Autowired
    private RatingsDao ratingsDao;

    @Override
    public void ratePost(Integer postId, Integer star, Integer userId) throws CustomException {
        if (postId == null || star == null || userId == null) {
            throw new CustomException(CustomException.VALIDATION_ERROR, "postId, star, 和 userId 不能为空");
        }

        try {
            Ratings existingRating = ratingsDao.selectOne(new QueryWrapper<Ratings>().eq("post_id", postId).eq("user_id", userId));
            if (existingRating == null) {
                ratingsDao.insertPostRating(postId, userId, star);
            } else {
                ratingsDao.updatePostRating(postId, userId, star);
            }
            ratingsDao.updatePostStars(postId);
        } catch (Exception e) {
            throw new CustomException(CustomException.DATABASE_ERROR, "评分的时候发生错误： " + e.getMessage());
        }
    }
}
