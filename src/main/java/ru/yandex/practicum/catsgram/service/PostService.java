package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    public PostService (UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        log.debug("All posts count: " + posts.size());
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) != null) {
            log.debug("Creating post: " + post);
            posts.add(post);
            return post;
        } else {
            log.warn("User not found.");
            throw new UserNotFoundException("User with email <" + post.getAuthor() + "> not found.");
        }
    }
}