package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.FeedService;

import java.util.List;


@RestController()
@RequestMapping("/feed/friends")
public class PostFeedController {

    private final FeedService feedService;

    public PostFeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    /*@PostMapping
    List<Post> getFriendsFeed(@RequestBody FeedParams feedParams) {
        if (!SORTS.contains(feedParams.getSort())) {
            throw new IncorrectParameterException("sort");
        }
        if (feedParams.getSize() == null || feedParams.getSize() <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (feedParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String friendEmail : feedParams.getFriendsEmails()) {
            result.addAll(postService.findPostsByUser(friendEmail, feedParams.getSize(), feedParams.getSort()));
        }
        return result;
    } */

    @GetMapping
    List<Post> getFriendsFeed(@RequestParam("userId") String userId, @RequestParam(defaultValue = "10") int max) {
        return feedService.getFeedFor(userId, max);
    }
}
