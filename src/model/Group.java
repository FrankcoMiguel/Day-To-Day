package model;

import java.sql.Date;
import java.time.LocalDate;

public class Group {

    private int id;
    private String name;
    private LocalDate add_date;
    private LocalDate mod_date;

    private int total_completed;
    private int total_tasks;

    public Group() {
        this.add_date = LocalDate.now();
        this.mod_date = LocalDate.now();
    }

    public Group(String name) {
        this.name = name;
        this.add_date = LocalDate.now();
        this.mod_date = LocalDate.now();
    }

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

    public int getTotal_completed() {
        return total_completed;
    }

    public void setTotal_completed(int total_completed) {
        this.total_completed = total_completed;
    }

    public int getTotal_tasks() {
        return total_tasks;
    }

    public void setTotal_tasks(int total_tasks) {
        this.total_tasks = total_tasks;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", add_date=" + add_date +
                ", mod_date=" + mod_date +
                ", total_completed=" + total_completed +
                ", total_tasks=" + total_tasks +
                '}';
    }
}
