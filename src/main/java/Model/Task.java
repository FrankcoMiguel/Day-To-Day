package Model;

import java.time.LocalDate;

public class Task {

    private int Id;
    private String Name;
    private LocalDate Deadline;
    private boolean Status;

    public Task(String name, LocalDate deadline, boolean status) {
        Name = name;
        Deadline = deadline;
        Status = status;
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Deadline=" + Deadline +
                ", Status=" + Status +
                '}';
    }
}
