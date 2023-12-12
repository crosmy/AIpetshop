package edu.whu.security;

import edu.whu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个UserDetailsService的Bean，从数据库中读取用户和权限信息
 */
@Service
public class DbUserDetailService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.whu.domain.User user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户 " + username + " 没有找到");
        }

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    private static GrantedAuthority[] getAuthorities(edu.whu.domain.User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getRole();
        String[] authorityStrs = role.split(",");
        for (String auth : authorityStrs)
            authorities.add(new SimpleGrantedAuthority(auth));
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

}
