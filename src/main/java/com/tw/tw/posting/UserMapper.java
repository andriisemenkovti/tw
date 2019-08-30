package com.tw.tw.posting;

import com.tw.tw.dao.User;

import java.util.ArrayList;

public class UserMapper {
    public static UserDTO toDTO(final User user) {
        return new UserDTO(
                user.getUserId(),
                new ArrayList<>(user.getFollowing()),
                user.getUserName()
        );
    }
}
