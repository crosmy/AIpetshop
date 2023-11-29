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

    @PostMapping("/{username}")
    public String uploadPhoto(String username, String photo) throws CustomException {
        userService.uploadPhoto(username, photo);
        return "上传头像成功";
    }



}

