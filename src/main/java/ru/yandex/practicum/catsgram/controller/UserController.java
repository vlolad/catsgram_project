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
        System.out.println("Зашли в POST");
        System.out.println("Данные: " + user);
        try {
            checkNewUser(user);
        } catch (InvalidEmailException | UserAlreadyExistException exc) {
            System.out.println("Ловим ошибки.");
            System.out.println(exc.getMessage());
            return null;
        }
        System.out.println("Ошибки нет.");
        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        System.out.println("Зашли в PUT");
        System.out.println("Данные: " + user);
        try {
            checkNewUser(user);
        } catch (InvalidEmailException exc) {
            System.out.println("Ловим ошибку что мыла нет.");
            System.out.println(exc.getMessage());
            return null;
        } catch (UserAlreadyExistException exc) {
            System.out.println("Ловим ошибку что пользователь уже есть.");
            users.remove(user);
            users.add(user);
            return user;
        }
        System.out.println("Ошибки нет.");
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
