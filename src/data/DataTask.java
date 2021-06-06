package data;

import model.Group;
import model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataTask implements IData {

    private final DBConnection dbConnection = DBConnection.getInstance();
    private Task task;

    public DataTask() {
    }

    public DataTask(Task task) {
        this.task = task;
    }

    //Create Table
    @Override
    public boolean create() {
        try {

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(Queries.CREATE_TASK_TABLE.getQuery());
            preparedStatement.executeUpdate();

        } catch (Exception e){

            System.out.println("Error creating the Task table \n" + e);
            return false;

        }

        return true;
    }

    //Add Task
    @Override
    public boolean add() {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.ADD_TASK.getQuery());
            ps.setObject(1, null);
            ps.setString(2,task.getName());
            ps.setString(3, task.getDescription());
            ps.setInt(4, task.isComplete() ? 1 : 0);
            ps.setString(5, task.getDeadline_date().toString());
            ps.setString(6, task.getDeadline_time().toString());
            ps.setString(7, task.getPriority());
            ps.setString(8, task.getAdd_date().toString());
            ps.setString(9, task.getMod_date().toString());
            ps.setInt(10, task.getGroup_id());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error adding Task");
            return false;

        }

        return true;
    }

    //Edit Task
    @Override
    public boolean edit() {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.MODIFY_TASK.getQuery());
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getDeadline_date().toString());
            ps.setString(4, task.getDeadline_time().toString());
            ps.setString(5, task.getPriority());
            ps.setInt(6, task.getGroup_id());
            ps.setString(7, task.getMod_date().toString());
            ps.setInt(8, task.getId());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error modifying Task");
            return false;

        }

        return true;
    }

    //Remove Task
    @Override
    public boolean remove() {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.REMOVE_TASK.getQuery());
            ps.setInt(1, task.getId());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error removing Task");
            return false;

        }

        return true;
    }

    //Get Task Due Soon
    public List<Task> getTasks(){

        List<Task> tasks = new ArrayList<>();

        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.GET_TASK_SUMMARY.getQuery());
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setComplete(resultSet.getInt(3) == 1);
                task.setDeadline_date(LocalDate.parse(resultSet.getString(4)));
                task.setDeadline_time(LocalTime.parse(resultSet.getString(5)));
                task.setPriority(resultSet.getString(6));
                task.setGroup(resultSet.getString(7));
                tasks.add(task);
            }

        } catch (Exception e) {

            System.out.println("Error getting the task summary " + e);

        }

        return tasks;
    }

    public boolean setComplete() {

        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.COMPLETE_TASK.getQuery());
            ps.setInt(1, task.isComplete() ? 0 : 1);
            ps.setInt(2, task.getId());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error setting Task complete");
            return false;

        }

        return true;

    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.GET_TASKS.getQuery());
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setComplete(resultSet.getInt(4) == 1);
                task.setDeadline_date(LocalDate.parse(resultSet.getString(5)));
                task.setDeadline_time(LocalTime.parse(resultSet.getString(6)));
                task.setPriority(resultSet.getString(7));
                task.setGroup(resultSet.getString(8));
                tasks.add(task);
            }

        } catch (Exception e) {

            System.out.println("Error getting the tasks " + e);

        }

        return tasks;
    }

}
