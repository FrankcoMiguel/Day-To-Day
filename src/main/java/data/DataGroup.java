package data;

import model.Group;
import model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataGroup implements IData {

    private final DBConnection dbConnection = DBConnection.getInstance();
    private Group group;

    public DataGroup() {
    }

    public DataGroup(Group group) {
        this.group = group;
    }

    //Create Table
    @Override
    public boolean create(){
        try {

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(Queries.CREATE_GROUP_TABLE.getQuery());
            preparedStatement.executeUpdate();

        } catch (Exception e){

            System.out.println("Error creating the List table \n" + e);
            return false;

        }

        return true;

    }

    //Add List
    public boolean add() {

        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.ADD_GROUP.getQuery());
            ps.setObject(1, null);
            ps.setString(2,group.getName());
            ps.setString(3, group.getAdd_date().toString());
            ps.setString(4, group.getMod_date().toString());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error adding Group");
            return false;

        }

        return true;
    }

    //Edit List
    @Override
    public boolean edit(){

        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.MODIFY_GROUP.getQuery());
            ps.setString(1, group.getName());
            ps.setString(2, group.getMod_date().toString());
            ps.setInt(3, group.getId());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error modifying Group");
            return false;

        }

        return true;

    }

    //Remove List
    @Override
    public boolean remove(){
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(Queries.REMOVE_GROUP.getQuery());
            ps.setInt(1, group.getId());
            dbConnection.getConnection().prepareStatement("PRAGMA foreign_keys = ON;").execute();

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error adding Group");
            return false;

        }

        return true;
    }

    //Get All Lists
    public List<Group> get() {

        List<Group> groups = new ArrayList<>();

        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.GET_GROUPS.getQuery());
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setAdd_date(LocalDate.parse(resultSet.getString(3)));
                group.setMod_date(LocalDate.parse(resultSet.getString(4)));
                group.setTotal_completed(resultSet.getInt(5));
                group.setTotal_tasks(resultSet.getInt(6));
                groups.add(group);
            }

        } catch (Exception e) {

            System.out.println("Error getting the groups " + e);

        }

        return groups;

    }

    //Get first 4 Lists order by Tasks completed
    public List<Group> getByTasks() {

        List<Group> groups = new ArrayList<>();

        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.GET_GROUP_SUMMARY.getQuery());
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setTotal_completed(resultSet.getInt(3));
                group.setTotal_tasks(resultSet.getInt(4));
                groups.add(group);
            }

        } catch (Exception e) {

            System.out.println("Error getting the group summary " + e);

        }

        return groups;

    }


}
