package com.tw.tw.api;

import com.tw.tw.following.FollowingAPI;
import com.tw.tw.posting.PostDTO;
import com.tw.tw.posting.PostingAPI;
import com.tw.tw.posting.UserDTO;
import com.tw.tw.timeline.TimelineService;
import com.tw.tw.wall.WallService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController(SocialNetworkController.PATH)
public class SocialNetworkController {
    public static final String PATH = "/tweeter";
    private final PostingAPI postingAPI;
    private final WallService wallService;
    private final TimelineService timelineService;
    private final FollowingAPI followingAPI;

    /**
     * Creates new post an register user in system if not present.
     *
     * @param userName
     * @param content
     * @return
     */
    @PostMapping(PATH + "/post")
    public Mono<PostDTO> tweet(@RequestParam String userName,
                               @RequestBody String content) {
        log.info("Posting message with username {}", userName);
        return Mono
                .just(postingAPI.postNewMessage(content, userName))
                .log();
    }

    /**
     * All user message in  reverse chronological order.
     *
     * @param userName
     * @return
     */
    @GetMapping(PATH + "/wall")
    public Flux<PostDTO> wall(@RequestParam String userName) {
        return wallService.getCurrentUserPosts(userName)
                .doOnError(throwable ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, throwable.getMessage()))
                .log();
    }

    /**
     * Posts of all followed users.
     *
     * @param userName
     * @return
     */
    @GetMapping(PATH + "/timeline")
    public Flux<PostDTO> timeline(@RequestParam String userName) {
        return timelineService.getAllFollowedPosts(userName)
                .log();
    }

    /**
     * Source user follow target user as a result of execution.
     *
     * @param source
     * @param target
     * @return
     */
    @PutMapping(PATH + "/follow")
    public Mono<UserDTO> follow(@RequestParam String source,
                                @RequestParam String target) {
        return Mono.just(followingAPI.follow(source, target))
                .log();
    }
}
