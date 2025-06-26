package com.example.Coordinater.repository;

import com.example.Coordinater.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public UserRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<User> result = jdbc.query(sql, params, new BeanPropertyRowMapper<>(User.class));
        return result.stream().findFirst();
    }

    public void create(User user) {
        String sql = "INSERT INTO users (username, password, role, enabled) " +
                "VALUES (:username, :password, :role, :enabled)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole().name())
                .addValue("enabled", user.isEnabled());

        jdbc.update(sql, params);
    }

    public void update(User user) {
        String sql = "UPDATE users SET username = :username, password = :password, " +
                "role = :role, enabled = :enabled WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole().name())
                .addValue("enabled", user.isEnabled());

        jdbc.update(sql, params);
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update(sql, params);
    }
}