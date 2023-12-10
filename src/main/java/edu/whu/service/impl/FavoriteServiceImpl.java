package edu.whu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.dao.FavoriteDao;
import edu.whu.domain.Favorite;
import edu.whu.exception.CustomException;
import edu.whu.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteDao, Favorite> implements IFavoriteService {

    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public void addFavorite(Integer userId, Integer postId) throws CustomException {
        try {
            if (favoriteDao.countFavorites(userId, postId) == 0) {
                favoriteDao.insertFavorite(userId, postId);
            }
        } catch (Exception e) {
            throw new CustomException(CustomException.FAVORITE_ADD_ERROR, "添加收藏时出错");
        }
    }

    @Override
    public void removeFavorite(Integer userId, Integer postId) throws CustomException {
        try {
            if (favoriteDao.countFavorites(userId, postId) > 0) {
                favoriteDao.deleteFavorite(userId, postId);
            }
        } catch (Exception e) {
            throw new CustomException(CustomException.FAVORITE_REMOVE_ERROR, "移除收藏时出错");
        }
    }

    @Override
    public boolean isFavorite(Integer userId, Integer postId) throws CustomException {
        try {
            return favoriteDao.countFavorites(userId, postId) > 0;
        } catch (Exception e) {
            throw new CustomException(CustomException.FAVORITE_CHECK_ERROR, "检查收藏状态时出错");
        }
    }

    @Override
    public List<Favorite> getFavoriteList(Integer userId) throws CustomException {
        try {
            return favoriteDao.getFavoritesByUserId(userId);
        } catch (Exception e) {
            throw new CustomException(CustomException.FAVORITE_LIST_ERROR, "获取收藏列表时出错");
        }
    }
}
