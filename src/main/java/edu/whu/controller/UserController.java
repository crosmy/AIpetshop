package edu.whu.controller;


import edu.whu.domain.User;
import edu.whu.exception.CustomException;
import edu.whu.service.IUserService;
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
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) throws CustomException {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.registerNewUser(newUser);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) throws CustomException {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new CustomException(CustomException.USER_NOT_FOUND, "用户名或密码为空");
        }
        if (userService.getUser(user.getUsername()) == null) {
            throw new CustomException(CustomException.USER_NOT_FOUND, "用户不存在");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(),userDetails.getPassword());
        return userService.authenticateUser(isPasswordMatch, userDetails);
    }


}

