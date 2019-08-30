package com.tw.tw.following;

import com.tw.tw.posting.UserDTO;
import org.springframework.transaction.annotation.Transactional;

public interface FollowingAPI {
    @Transactional
    UserDTO follow(String sourceUserName, String targetUserName);
}
