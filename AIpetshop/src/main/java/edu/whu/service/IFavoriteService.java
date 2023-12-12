package edu.whu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.domain.Favorite;
import edu.whu.exception.CustomException;

import java.util.List;

public interface IFavoriteService extends IService<Favorite> {

    void addFavorite(Integer userId, Integer postId) throws CustomException;

    void removeFavorite(Integer userId, Integer postId) throws CustomException;

    boolean isFavorite(Integer userId, Integer postId) throws CustomException;

    List<Favorite> getFavoriteList(Integer userId) throws CustomException;
}
