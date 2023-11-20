package edu.whu.service.impl;

import edu.whu.domain.User;
import edu.whu.dao.UserDao;
import edu.whu.exception.CustomException;
import edu.whu.security.JwtTokenUtil;
import edu.whu.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static edu.whu.exception.CustomException.UNAUTHORIZED;
import static edu.whu.exception.CustomException.USER_ALREADY_EXISTS;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Autowired
    UserDao userDao;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }


    @Override
    public User registerNewUser(User newUser) throws CustomException {
        if (userDao.getUser(newUser.getUsername()) != null) {
            throw new CustomException(USER_ALREADY_EXISTS, "用户已存在，重新选择用户名");
        }
        userDao.insert(newUser);
        return newUser;
    }

    @Override
    public String authenticateUser(boolean isPasswordMatch, UserDetails userDetails) throws CustomException {
        if (!isPasswordMatch) {
            throw new CustomException(UNAUTHORIZED, "用户认证未通过");
        }
        return jwtTokenUtil.generateToken(userDetails);
    }



}
