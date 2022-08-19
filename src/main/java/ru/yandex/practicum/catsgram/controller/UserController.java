package ru.yandex.practicum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, User> allUsers() {
        log.info("Arrived GET-request at /users");
        return userService.allUsers();
    }

    @PostMapping
    public User create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        log.debug("Arrived POST-request at /users");
        return userService.create(user);
    }

    @PutMapping
    public User put(@RequestBody User user) throws InvalidEmailException {
        log.debug("Arrived PUT-request at /users");
        return userService.put(user);
    }
}
