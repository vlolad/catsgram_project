package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
public class PostFeedController {

    private final PostService postService;

    @Autowired
    public PostFeedController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/feed/friends")
    List<Post> getFriendsFeed(@RequestBody String params){
        log.debug("Arrived cringe GET-request to /feed/friends");
        ObjectMapper objectMapper = new ObjectMapper();
        FriendsParams friendsParams;
        try {
            log.debug("Trying to deserialize request.");
            String paramsFromString = objectMapper.readValue(params, String.class);
            log.debug("Trying to deserialize deserialized request..");
            friendsParams = objectMapper.readValue(paramsFromString, FriendsParams.class);
        } catch (JsonProcessingException e) {
            log.error("Deserialization failed.");
            throw new RuntimeException("Invalid JSON format", e);
        }

        if(friendsParams != null){
            log.debug("Searching for friend's posts...");
            List<Post> result = new ArrayList<>();
            for (String friend : friendsParams.friends) {
                result.addAll(postService.findAllByUserEmail(friend, friendsParams.size, friendsParams.sorted));
            }
            log.info("Posts found: {}", result.size());
            return result;
        } else {
            log.error("Searching failed.");
            throw new RuntimeException("Invalid parameters.");
        }
    }

    static class FriendsParams {
        private String sorted;

        private Integer size;

        private List<String> friends;

        public String getSorted() {
            return sorted;
        }

        public void setSort(String sort) {
            this.sorted = sort;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public List<String> getFriends() {
            return friends;
        }

        public void setFriends(List<String> friends) {
            this.friends = friends;
        }
    }
}
