package edu.whu.service;

import edu.whu.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Pet
 * @since 2023-11-14
 */
public interface IUserService extends IService<User> {
    /**
     * Functions: registerUser(User user), validateUserCredentials(String username,
     * String password), findUserById(int userId), updateUser(User user),
     * and other user-related business logic.
     */
}
