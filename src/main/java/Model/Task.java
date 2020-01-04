package Model;

import java.time.LocalDate;

public class Task {

    private int Id;
    private String Name;
    private LocalDate Deadline;
    private String Status;

    public Task(String name, LocalDate deadline, String status) {
        Name = name;
        Deadline = deadline;
        Status = status;
    }

    public Task (){}

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDate getDeadline() {
        return Deadline;
    }

    public void setDeadline(LocalDate deadline) {
        Deadline = deadline;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String   toString() {
        return "Task{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Deadline=" + Deadline +
                ", Status=" + Status +
                '}';
    }


}
