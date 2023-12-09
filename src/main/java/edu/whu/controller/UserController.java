package edu.whu.controller;


import edu.whu.domain.User;
import edu.whu.domain.UserDto;
import edu.whu.exception.CustomException;
import edu.whu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Api(tags = "用户相关接口")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "更新自己的头像")
    @PostMapping("/photo/{username}")
    public String uploadPhoto(@PathVariable String username, String photo) throws CustomException {
        userService.uploadPhoto(username, photo);
        return "上传头像成功";
    }

    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("/id/{userId}")
    public UserDto getUserDetailById(@PathVariable Integer userId) throws CustomException {
        return userService.getUserDtoById(userId);
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @GetMapping("/name/{username}")
    public UserDto getUserDetailByName(@PathVariable String username) throws CustomException {
        return userService.getUserDto(username);
    }



}

