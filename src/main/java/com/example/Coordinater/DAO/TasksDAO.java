package com.example.Coordinater.DAO;

import com.example.Coordinater.run.Location;
import com.example.Coordinater.run.TasksForEngineers;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TasksDAO {

    private static final Logger log =  LoggerFactory.getLogger(TasksDAO.class);
    private final JdbcClient jdbcClient;

    public TasksDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<TasksForEngineers> findAll(){
        return jdbcClient.sql("select * from tasks")
                .query(TasksForEngineers.class)
                .list();
    }
}
