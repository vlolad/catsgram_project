package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {

    private final HashSet<User> users = new HashSet<>();

    @GetMapping
    public HashSet<User> allUsers() {
        System.out.println(users);
        return users;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        try {
            checkNewUser(user);
        } catch (InvalidEmailException | UserAlreadyExistException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        try {
            checkNewUser(user);
        } catch (InvalidEmailException exc) {
            System.out.println(exc.getMessage());
            return null;
        } catch (UserAlreadyExistException exc) {
            users.remove(user);
            users.add(user);
            return user;
        }
        users.add(user);
        return user;
    }

    private void checkNewUser(User user) throws InvalidEmailException, UserAlreadyExistException {
        if (user.getEmail().isBlank() || user.getEmail() == null) {
            throw new InvalidEmailException("There is no email in request body.");
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException("User with such Email is already exists.");
        }
    }
}
