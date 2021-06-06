package service;

import data.DataTask;
import model.Task;

import java.util.List;


public class TaskService implements IService {

    private final DataTask dataTask;

    public TaskService(Task task) {
        dataTask = new DataTask(task);
    }

    public TaskService() {
        dataTask = new DataTask();
    }

    @Override
    public void create() {
        dataTask.create();
    }

    @Override
    public boolean add() {
        return dataTask.add();
    }

    @Override
    public boolean update() {
        return dataTask.edit();
    }

    @Override
    public boolean delete() {
        return dataTask.remove();
    }

    public List<Task> getSummary(){
        return dataTask.getTasks();
    }

    public boolean setComplete() { return dataTask.setComplete(); }

    public List<Task> getTasks(){
        return dataTask.getAllTasks();
    }

}
