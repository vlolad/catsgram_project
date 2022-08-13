package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;

    public User(String email, String nickname, LocalDate birthdate) {
        this.email = email;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        User otherUser = (User) obj;
        return Objects.equals(email, otherUser.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
