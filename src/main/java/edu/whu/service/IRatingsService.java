package edu.whu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.domain.Ratings;
import edu.whu.exception.CustomException;

public interface IRatingsService extends IService<Ratings> {
    void ratePost(Integer postId, Integer star, Integer userId) throws CustomException;
}
