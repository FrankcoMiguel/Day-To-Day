package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {

    private int id;
    private String name;
    private String description;
    private boolean complete;
    private LocalDate deadline_date;
    private LocalTime deadline_time;
    private String priority;
    private LocalDate add_date;
    private LocalDate mod_date;
    private int group_id;
    private String group;

    public Task(String name, String description, LocalDate deadline_date, LocalTime deadline_time, String priority, int group_id) {
        this.name = name;
        this.description = description;
        this.complete = false;
        this.deadline_date = deadline_date;
        this.deadline_time = deadline_time;
        this.priority = priority;
        this.add_date = LocalDate.now();
        this.mod_date = LocalDate.now();
        this.group_id = group_id;
    }

    public Task () {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDate getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(LocalDate deadline_date) {
        this.deadline_date = deadline_date;
    }

    public LocalTime getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(LocalTime deadline_time) {
        this.deadline_time = deadline_time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getAdd_date() {
        return add_date;
    }

    public void setAdd_date(LocalDate add_date) {
        this.add_date = add_date;
    }

    public LocalDate getMod_date() {
        return mod_date;
    }

    public void setMod_date(LocalDate mod_date) {
        this.mod_date = mod_date;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", complete=" + complete +
                ", deadline_date=" + deadline_date +
                ", deadline_time=" + deadline_time +
                ", priority='" + priority + '\'' +
                ", add_date=" + add_date +
                ", mod_date=" + mod_date +
                ", group_id=" + group_id +
                ", group='" + group + '\'' +
                '}';
    }
}
