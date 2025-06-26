package com.example.Coordinater.repository;

import com.example.Coordinater.entity.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

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
        String sql = "INSERT INTO tasks (title, started_on, completed_on, location, assigned_to) " +
                "VALUES (:title, :startedOn, :completedOn, :location, :assignedTo)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("startedOn", task.getStartedOn())
                .addValue("completedOn", task.getCompletedOn())
                .addValue("location", task.getLocation())
                .addValue("assignedTo", task.getAssignedTo());

        jdbc.update(sql, params);
    }

    public void update(Task task, int id) {
        String sql = "UPDATE tasks SET title = :title, started_on = :startedOn, completed_on = :completedOn, " +
                "location = :location, assigned_to = :assignedTo WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", task.getTitle())
                .addValue("startedOn", task.getStartedOn())
                .addValue("completedOn", task.getCompletedOn())
                .addValue("location", task.getLocation())
                .addValue("assignedTo", task.getAssignedTo())
                .addValue("id", id);

        jdbc.update(sql, params);
    }

    public void assignTaskToEngineer(int taskId, String username) {
        String sql = "UPDATE tasks SET assigned_to = :username WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username)
                .addValue("id", taskId);
        jdbc.update(sql, params);
    }


    public void delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update(sql, params);
    }

    public int count() {
        return jdbc.query("SELECT * FROM tasks", new BeanPropertyRowMapper<>(Task.class)).size();
    }

    public List<Task> findByLocation(String location) {
        String sql = "SELECT * FROM tasks WHERE location = :location";
        MapSqlParameterSource params = new MapSqlParameterSource("location", location);
        return jdbc.query(sql, params, new BeanPropertyRowMapper<>(Task.class));
    }

    public void saveAll(List<Task> tasks) {
        tasks.forEach(this::create);
    }
}
