package com.tw.tw.posting;

import com.tw.tw.dao.Post;

public class PostMapper {
    public static PostDTO toDTO(final Post post, String userName) {
        return new PostDTO(post.getUserId(),
                post.getContent(),
                userName,
                post.getCreatedAt().toLocalDateTime());
    }

}
