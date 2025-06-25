package com.example.Coordinater.repository;

import com.example.Coordinater.entity.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public TaskRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Task> findAll() {
        return jdbc.query("SELECT * FROM tasks", new BeanPropertyRowMapper<>(Task.class));
    }

    public Optional<Task> findById(Integer id) {
        String sql = "SELECT * FROM tasks WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Task> result = jdbc.query(sql, params, new BeanPropertyRowMapper<>(Task.class));
        return result.stream().findFirst();
    }


    public void create(Task task) {
        String sql = "INSERT INTO tasks (title, started_on, completed_on, location) " +
                "VALUES (:title, :startedOn, :completedOn, :location)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("startedOn", task.getStartedOn())
                .addValue("completedOn", task.getCompletedOn())
                .addValue("location", task.getLocation());

        jdbc.update(sql, params);
    }


    public void update(Task task, int id) {
        String sql = "UPDATE tasks SET title = :title, started_on = :startedOn, completed_on = :completedOn, location = :location WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("startedOn", task.getStartedOn())
                .addValue("completedOn", task.getCompletedOn())
                .addValue("location", task.getLocation())
                .addValue("id", id);

        jdbc.update(sql, params);
    }


    public void delete(Integer id) {
        var sql = "DELETE FROM tasks WHERE id = :id";
        var params = new MapSqlParameterSource("id", id);
        int updated = jdbc.update(sql, params);
        Assert.state(updated == 1, "Failed to delete task " + id);
    }

    public int count() {
        return jdbc.query("SELECT * FROM tasks", new BeanPropertyRowMapper<>(Task.class)).size();
    }

    public List<Task> findByLocation(String location) {
        var sql = "SELECT * FROM tasks WHERE location = :location";
        var params = new MapSqlParameterSource("location", location);
        return jdbc.query(sql, params, new BeanPropertyRowMapper<>(Task.class));
    }

    public void saveAll(List<Task> tasks) {
        tasks.forEach(this::create);
    }
}
