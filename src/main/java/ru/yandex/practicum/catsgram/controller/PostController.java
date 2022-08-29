package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
@Slf4j
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        log.debug("Arrived GET-request in /posts");
        Integer from = page * size;
        if(!(sort.equals("asc") || sort.equals("desc"))){
            throw new IllegalArgumentException();
        }
        if(page < 0 || size <= 0){
            throw new IllegalArgumentException();
        }
        return postService.findAll(size, from, sort);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        log.debug("Arrived POST-request in /post");
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Post findPost(@PathVariable("postId") Integer postId){
        log.debug("Arrived GET-request in /post/{}", postId);
        return postService.findPostById(postId);
    }
}