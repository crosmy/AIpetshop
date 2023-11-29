package edu.whu.domain;

import lombok.Data;

@Data
public class LoginDto {
    String token;

    Integer userId;
}
