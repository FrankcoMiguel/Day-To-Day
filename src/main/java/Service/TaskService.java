package Service;


import Data.DBConnection;
import Data.TaskQueries;
import Model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

interface ITaskService {

    boolean CreateTaskTable();

    boolean AddTask(Task task);

    void GetAll();

}

public class TaskService implements ITaskService {

    private DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public boolean CreateTaskTable() {

        try {

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(TaskQueries.CREATE.getQuery());
            preparedStatement.executeUpdate();
            System.out.println("Table created successfully");
            return true;

        } catch (Exception e){

            System.out.println("Error creating the task table \n" + e);
            return false;

        }

    }

    @Override
    public boolean AddTask(Task task) {

        try {

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(TaskQueries.ADD.getQuery());
            preparedStatement.setString(1,task.getName());
            preparedStatement.setDate(2,java.sql.Date.valueOf(task.getDeadline()));
            preparedStatement.setString(3,task.getStatus());
            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e){

            System.out.println("Error adding a task into the table \n" + e);
            return false;

        }


    }

    @Override
    public void GetAll() {

        List<Task> tasks = new ArrayList<>();
        try{

            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(TaskQueries.READ.getQuery());
            while (resultSet.next()){

                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDeadline(resultSet.getDate(3).toLocalDate());
                task.setStatus(resultSet.getString(4));
                tasks.add(task);

            }

        } catch (Exception e){

            System.out.println("Error getting all tasks from the table \n" + e);

        }

    }
}
