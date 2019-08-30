package com.tw.tw.following;

import com.tw.tw.dao.User;
import com.tw.tw.dao.UserDao;
import com.tw.tw.posting.UserDTO;
import com.tw.tw.posting.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
public class FollowingImpl implements FollowingAPI {
    private final UserDao userDao;

    @Override
    public UserDTO follow(String sourceUserName, String targetUserName) {
        return follow(findUserId(sourceUserName), findUserId(targetUserName));
    }

    private Long findUserId(String targetUserName) {
        return userDao.findByUserName(targetUserName).map(User::getUserId).orElseThrow();
    }

    UserDTO follow(Long sourceUserId, Long targetUserId) {
        Supplier<UserFollowingException> supplier = () -> new UserFollowingException(format("Unable to follow user with ids: [%s] and [%s]", sourceUserId, targetUserId));
        var source = userDao.findWithFollowingByUserId(sourceUserId).orElseThrow(supplier);
        var target = userDao.findById(targetUserId).orElseThrow(supplier);
        source.getFollowing().add(target);
        User save = userDao.save(source);
        log.info("User with id:[{}] follows user with id:[{}]", source.getUserId(), target.getUserId());
        log.info("Actual entity state: " + save);
        return UserMapper.toDTO(save);
    }

}
