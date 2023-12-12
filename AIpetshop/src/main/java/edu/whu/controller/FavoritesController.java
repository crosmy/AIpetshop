package edu.whu.controller;

import edu.whu.domain.Favorite;
import edu.whu.exception.CustomException;
import edu.whu.service.IFavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="收藏相关接口")
@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    private IFavoriteService favoriteService;

    @ApiOperation(value = "添加帖子到收藏")
    @PostMapping("/favorite/add")
    public String addFavorite(@RequestParam Integer userId, @RequestParam Integer postId) throws CustomException {
        favoriteService.addFavorite(userId, postId);
        return "添加收藏成功";
    }

    @ApiOperation(value = "从收藏中移除帖子")
    @DeleteMapping("/favorite/delete")
    public String removeFavorite(@RequestParam Integer userId, @RequestParam Integer postId) throws CustomException {
        favoriteService.removeFavorite(userId, postId);
        return "移除收藏成功";
    }

    @ApiOperation(value = "获取用户的收藏状态", notes = "参数：userId, postId")
    @GetMapping("/favorite")
    public boolean isFavorite(@RequestParam Integer userId, @RequestParam Integer postId) throws CustomException {
        return favoriteService.isFavorite(userId, postId);
    }

    @ApiOperation(value = "获取用户的收藏列表", notes = "参数：userId")
    @GetMapping("/favorite/list")
    public List<Favorite> getFavoriteList(@RequestParam Integer userId) throws CustomException {
        return favoriteService.getFavoriteList(userId);
    }
}
