package edu.whu.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    /**
     * 除去密码以外的用户信息，用作展示
     */
    private Integer userId;

    private String username;

    private String email;

    private String role;

    private String profilePictureUrl;

    private String contactInfo;

    private LocalDateTime createdAt;

    private List<Post> posts;

    private List<Pet> pets;
}
