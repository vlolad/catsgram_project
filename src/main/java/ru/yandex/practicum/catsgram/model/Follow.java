package ru.yandex.practicum.catsgram.model;

public class Follow {

    private final User author;
    private final User subscriber;

    public Follow(User author, User subscriber) {
        this.author = author;
        this.subscriber = subscriber;
    }

    public User getAuthor() {
        return author;
    }

    public User getSubscriber() {
        return subscriber;
    }
}
