package com.tw.tw.wall;

import com.tw.tw.posting.PostDTO;
import com.tw.tw.posting.PostingAPI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class WallService {
    private final PostingAPI postingAPI;

    public Flux<PostDTO> getCurrentUserPosts(String userName) {
        return Flux.fromIterable(postingAPI.getCurrentUserMessages(userName));
    }
}
