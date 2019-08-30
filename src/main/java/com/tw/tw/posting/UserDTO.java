package com.tw.tw.posting;

import com.tw.tw.dao.User;
import lombok.Value;

import java.util.List;

@Value
public final class UserDTO {
    private Long userId;
    private List<User> following;
    private String userName;
}
