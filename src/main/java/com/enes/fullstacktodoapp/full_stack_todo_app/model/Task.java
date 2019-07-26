package com.enes.fullstacktodoapp.full_stack_todo_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Task name is requried")
    private String taskName;
    @NotBlank(message = "Task Identifier name is requried")
    @Size(min = 3,max = 5, message = "Please use 3 to 5 characters")
    @Column(updatable = false,unique = true)
    private String taskIdentifier;
    @NotBlank(message = "Description is requried")
    private String desc;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date start_date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date end_date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create_at;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updated_at;
    private boolean completed;

    @OneToMany(mappedBy="parent",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonManagedReference
    private List<Task> subTasks;

    @ManyToOne
    @JoinColumn()
    @JsonBackReference
    private Task parent;

    public Task() {
    }

    public Task(@NotBlank(message = "Task name is requried") String taskName, @NotBlank(message = "Task Identifier name is requried") @Size(min = 3, max = 5, message = "Please use 3 to 5 characters") String taskIdentifier, @NotBlank(message = "Description is requried") String desc) {
        this.taskName = taskName;
        this.taskIdentifier = taskIdentifier;
        this.desc = desc;
    }

    @PrePersist
    protected void onCreate(){
        this.create_at=new Date();
        this.taskIdentifier=this.taskIdentifier.toUpperCase();
        this.completed=false;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at=new Date();
        this.taskIdentifier=this.taskIdentifier.toUpperCase();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskIdentifier() {
        return taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }
}
