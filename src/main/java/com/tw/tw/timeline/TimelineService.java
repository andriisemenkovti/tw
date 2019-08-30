package com.tw.tw.timeline;

import com.tw.tw.posting.PostDTO;
import com.tw.tw.posting.PostingAPI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class TimelineService {
    private final PostingAPI postingAPI;

    public Flux<PostDTO> getAllFollowedPosts(String userName) {
        return Flux.fromIterable(postingAPI.getFollowedUsersMessages(userName));
    }
}
