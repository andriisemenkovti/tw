package com.tw.tw.posting;

import com.tw.tw.dao.Post;
import com.tw.tw.dao.PostDao;
import com.tw.tw.dao.User;
import com.tw.tw.dao.UserDao;
import com.tw.tw.wall.WallEmptyException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
class PostingService {
    private final PostDao postDao;
    private final UserDao userDao;

    @Transactional
    public Post postMessage(String userName, String content) {
        Optional<User> user = userDao.findByUserName(userName);
        if (user.isEmpty()) {
            User entity = new User();
            entity.setFollowing(emptyList());
            entity.setUserName(userName);
            userDao.save(entity);
            return createNewPost(content, userDao.save(entity));
        }
        return createNewPost(content, user.orElseThrow());
    }

    @Transactional
    public List<Post> getFollowedUsersPosts(String userName) {
        User user = userDao.findByUserName(userName).orElseThrow();
        List<Long> followingUserIds = user.getFollowing()
                .stream()
                .map(User::getUserId)
                .collect(toList());
        return new ArrayList<>(postDao.findAllByUserId(followingUserIds));
    }

    public List<Post> getCurrentUserPosts(String userName) {
        try {
            List<Post> posts = new ArrayList<>(
                    postDao.findAllByUserId(
                            userDao.findByUserName(userName)
                                    .map(User::getUserId)
                                    .orElseThrow(),
                            Sort.by(Sort.Direction.DESC, "createdAt"))
            );
            log.debug("Found posts for user {}", userName);
            log.debug(Arrays.toString(posts.toArray()));
            return posts;
        } catch (NoSuchElementException e) {
            throw new WallEmptyException(userName);
        }
    }

    Post createNewPost(String content, User user) {
        final Post post = new Post();
        post.setContent(content);
        post.setUserId(user.getUserId());
        return postDao.save(post);
    }
}
