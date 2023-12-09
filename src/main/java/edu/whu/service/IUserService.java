package edu.whu.service;

import edu.whu.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.domain.UserDto;
import edu.whu.exception.CustomException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IUserService extends IService<User> {
    User getUser(String username);
    String authenticateUser(boolean isPasswordMatch, UserDetails userDetails) throws CustomException;
    String registerNewUser(User newUser) throws CustomException;

    void uploadPhoto(String username, String photo) throws CustomException;

    UserDto getUserDtoById(Integer userId) throws CustomException;

    UserDto getUserDto(String username) throws CustomException;
}
