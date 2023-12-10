package edu.whu.controller;

import edu.whu.exception.CustomException;
import edu.whu.service.IPostService;
import edu.whu.service.IRatingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="评分相关接口")
@RestController
@RequestMapping("/star")
public class StarController {


    @Autowired
    private IRatingsService ratingsService;

    @ApiOperation(value = "评分帖子", notes = "用来评分帖子, star 为 1-5 之间的整数，参数用 ?postId=1&star=5&userId=1格式传递，评完分后同时更新帖子的平均分")
    @PutMapping("/ratePost")
    public String ratePost(@RequestParam Integer postId, @RequestParam Integer star, @RequestParam Integer userId) throws CustomException {
        ratingsService.ratePost(postId, star, userId);
        return "成功评分帖子" + postId + "，评分为" + star + "星";
    }

}
