package com.tw.tw.posting;

import com.tw.tw.dao.Post;
import com.tw.tw.dao.User;
import com.tw.tw.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.tw.tw.posting.PostMapper.toDTO;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PostingImpl implements PostingAPI {
    private final PostingService postingService;
    private final UserDao userDao;

    @Override
    public PostDTO postNewMessage(String content, String userName) {
        Optional<Post> post = Optional.ofNullable(postingService.postMessage(userName, content));
        if (post.isPresent()) {
            return toDTO(post.get(), userName);
        } else {
            var user = new User();
            user.setUserName(userName);
            user.setFollowing(emptyList());
            return toDTO(postingService.postMessage(userName, content), userName);
        }
    }

    @Override
    public List<PostDTO> getFollowedUsersMessages(String userName) {
        return postingService.getFollowedUsersPosts(userName)
                .stream()
                .map(mapPostToDTO())
                .collect(toList());
    }

    @Override
    public List<PostDTO> getCurrentUserMessages(String userName) {
        return postingService.getCurrentUserPosts(userName)
                .stream()
                .map(mapPostToDTO())
                .collect(toList());
    }

    private Function<Post, PostDTO> mapPostToDTO() {
        return post -> toDTO(post, userDao.findById(post.getUserId())
                .map(User::getUserName)
                .orElseThrow());
    }
}
