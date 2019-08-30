package com.tw.tw.posting;

import java.util.List;

public interface PostingAPI {
    PostDTO postNewMessage(String content, String userName);

    List<PostDTO> getCurrentUserMessages(String userName);

    List<PostDTO> getFollowedUsersMessages(String userName);
}
