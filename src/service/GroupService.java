package service;

import data.DataGroup;
import model.Group;

import java.util.List;

public class GroupService implements IService {

    private final DataGroup dataGroup;

    public GroupService(Group group) {
        dataGroup = new DataGroup(group);
    }

    public GroupService() {
        dataGroup = new DataGroup();
    }

    @Override
    public void create() {
        dataGroup.create();
    }

    @Override
    public boolean add() {
        return dataGroup.add();
    }

    @Override
    public boolean update() {
        return dataGroup.edit();
    }

    @Override
    public boolean delete() {
        return dataGroup.remove();
    }

    public List<Group> getLists() { return dataGroup.get(); }

    public List<Group> getSummary() { return dataGroup.getByTasks(); }

}