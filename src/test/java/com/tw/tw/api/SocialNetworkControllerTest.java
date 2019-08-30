package com.tw.tw.api;

import com.tw.tw.environment.BaseIntegrationTest;
import com.tw.tw.posting.PostDTO;
import com.tw.tw.posting.UserDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * To test all usecases in one test class it is necessary to use fixed order.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocialNetworkControllerTest extends BaseIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void a_tweet() {
        webTestClient.post()
                .uri("/tweeter/post?userName=Test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("Hello")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(PostDTO.class);
    }

    @Test
    public void b_wall() {
        webTestClient.get()
                .uri("/tweeter/wall?userName=Test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(PostDTO.class);
    }

    @Test
    public void c_follow() {
        //posting and creating new user then
        webTestClient.post()
                .uri("/tweeter/post?userName=Test1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("Hello")
                .exchange();

        webTestClient.put()
                .uri("/tweeter/follow?source=Test&target=Test1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(UserDTO.class);
    }

    @Test
    public void d_timeline() {
        webTestClient.get()
                .uri("/tweeter/timeline?userName=Test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(PostDTO.class);
    }

}