package edu.whu.controller;


import edu.whu.domain.LoginDto;
import edu.whu.domain.User;
import edu.whu.exception.CustomException;
import edu.whu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@Api(tags = "用户认证相关接口")
public class AuthenticationController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @ApiOperation(value = "注册用户",notes = "注册用户")
    @PostMapping("/register")
    public String registerUser(@RequestBody User newUser) throws CustomException {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.registerNewUser(newUser);
    }

    @ApiOperation(value = "登录",notes = "登录")
    @PostMapping("/login")
    public LoginDto login(@RequestBody User user) throws CustomException {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new CustomException(CustomException.USER_NOT_FOUND, "用户名或密码为空");
        }
        if (userService.getUser(user.getUsername()) == null) {
            throw new CustomException(CustomException.USER_NOT_FOUND, "用户不存在");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(),userDetails.getPassword());
        LoginDto loginDto = new LoginDto();
        String token = userService.authenticateUser(isPasswordMatch, userDetails);
        Integer userId = userService.getUser(user.getUsername()).getUserId();
        loginDto.setToken(token);
        loginDto.setUserId(userId);
        return loginDto;

    }
}
