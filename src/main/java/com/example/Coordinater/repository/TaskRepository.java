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
        var sql = "INSERT INTO tasks(id, title, started_on, completed_on, location) " +
                "VALUES (:id, :title, :started_on, :completed_on, :location)";
        var params = new MapSqlParameterSource()
                .addValue("id", task.getId())
                .addValue("title", task.getTitle())
                .addValue("started_on", task.getStartedOn())
                .addValue("completed_on", task.getCompletedOn())
                .addValue("location", task.getLocation());

        int updated = jdbc.update(sql, params);
        Assert.state(updated == 1, "Failed to create task " + task.getTitle());
    }

    public void update(Task task, Integer id) {
        var sql = "UPDATE tasks SET title = :title, started_on = :started_on, completed_on = :completed_on, location = :location " +
                "WHERE id = :id";
        var params = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("started_on", task.getStartedOn())
                .addValue("completed_on", task.getCompletedOn())
                .addValue("location", task.getLocation())
                .addValue("id", id);

        int updated = jdbc.update(sql, params);
        Assert.state(updated == 1, "Failed to update task " + task.getTitle());
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
