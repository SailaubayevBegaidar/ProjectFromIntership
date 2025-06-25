package com.example.Coordinater.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "started_on", nullable = false)
    private LocalDateTime startedOn;

    @Column(name = "completed_on", nullable = false)
    private LocalDateTime completedOn;

    @Column(nullable = false)
    private String location;

    @Column(name = "assigned_to")
    private String assignedTo; // username инженера


    public Task() {}


    public Task(String title, LocalDateTime startedOn, LocalDateTime completedOn, String location) {
        this.title = title;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
        this.location = location;
    }


    public Task(Integer id, String title, LocalDateTime startedOn, LocalDateTime completedOn, String location, String assignedTo) {
        this.id = id;
        this.title = title;
        this.startedOn = startedOn;
        this.completedOn = completedOn;
        this.location = location;
        this.assignedTo = assignedTo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(LocalDateTime completedOn) {
        this.completedOn = completedOn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startedOn=" + startedOn +
                ", completedOn=" + completedOn +
                ", location='" + location + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                '}';
    }
}
