package ru.yandex.practicum.catsgram.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.*;
import ru.yandex.practicum.catsgram.model.Follow;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FollowDaoImpl implements FollowDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        // получаем все подписки пользователя
        String sql = "select * from cat_follow " +
                "join cat_user cu on cat_follow.author_id = cu.id" +
                " where subscriber_id = ?"; // напишите подходящий SQL-запрос
        List<User> follows = jdbcTemplate.query(sql, (rs, rowNum) -> makeFollow(rs), userId);

        // выгружаем авторов, на которых подписан пользователь
        Set<User> authors = new HashSet<>(follows);

        // выгрузите и отсортируйте посты полученных выше авторов
        // не забудьте предусмотреть ограничение выдачи
        return authors.stream()
                .map(postDao::findPostsByUser)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(max)
                .collect(Collectors.toList());
    }

    private User makeFollow(ResultSet rs) throws SQLException {
        // реализуйте маппинг результата запроса в объект класса Follow

        return new User(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("nickname"));
    }
}
