package com.demo.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Task is mandatory")
    @Column(name = "task")
    private String task;

    @NotBlank(message = "Task Description is mandatory")
    @Column(name = "taskdesc")
    private String taskdesc;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "duedate")
    private Date duedate;


    @Column(name = "taskstatus")
    private boolean taskstatus;





    public Task() {}

    public Task(String task, String taskdesc, Date duedate) {
        this.task = task;
        this.taskdesc = taskdesc;
        this.duedate = duedate;
        this.taskstatus = false;
    }


    public boolean isTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(boolean taskstatus) {
        this.taskstatus = taskstatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }




    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
}