package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public Map<String, User> allUsers() {
        log.info("All users count: {}", users.size());
        return users;
    }

    public User create(User user) throws UserAlreadyExistException, InvalidEmailException {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            log.warn("Invalid email field.");
            throw new InvalidEmailException("Email cannot be empty.");
        }
        if (users.containsKey(user.getEmail())) {
            log.warn("User already exist.");
            throw new UserAlreadyExistException("User with Email " +
                    user.getEmail() + " already exists.");
        }
        log.debug("Creating user: " + user);
        users.put(user.getEmail(), user);
        return user;
    }

    public User put(User user) throws InvalidEmailException {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            log.warn("Invalid email field.");
            throw new InvalidEmailException("Email cannot be empty.");
        }
        log.debug("Update(creating) user: " + user);
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String email) {
        if (email == null) return null;
        return users.getOrDefault(email, null);
    }
}
