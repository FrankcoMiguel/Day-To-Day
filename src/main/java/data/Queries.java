package data;

public enum Queries {



    //Group Queries

    CREATE_GROUP_TABLE("CREATE TABLE IF NOT EXISTS [group](id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name VARCHAR NOT NULL UNIQUE, " +
                            "add_date DATE, " +
                            "mod_date DATE);"
    ),

    ADD_GROUP("INSERT INTO [group] VALUES (?,?,?,?);"),

    GET_GROUPS("SELECT g.id, g.name, g.add_date, g.mod_date, (select count(*) from task where group_id = g.id and status = 1) total_completed, " +
            "(select count(*) from task where group_id = g.id) total_tasks " +
            "FROM [group] g " +
            "LEFT JOIN task t " +
            "ON g.id = t.group_id " +
            "GROUP BY g.id, g.name " +
            "ORDER BY total_tasks desc;"),

    GET_GROUP_SUMMARY("SELECT g.id, g.name, (select count(*) from task where group_id = g.id and status = 1) total_completed, " +
            "(select count(*) from task where group_id = g.id) total_tasks " +
            "FROM [group] g " +
            "LEFT JOIN task t " +
            "ON t.group_id = g.id " +
            "GROUP BY g.id, g.name " +
            "ORDER BY total_tasks desc " +
            "LIMIT 4;"),

    MODIFY_GROUP("UPDATE [group] SET name = ? , " +
            "mod_date = ? " +
            "WHERE id = ?;"),


    REMOVE_GROUP("DELETE FROM [group] " +
            "WHERE id = ?;"),



    //Task Queries

    CREATE_TASK_TABLE("CREATE TABLE IF NOT EXISTS task (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name VARCHAR NOT NULL, " +
            "description VARCHAR, " +
            "status INTEGER NOT NULL, " +
            "deadline_date DATE, " +
            "deadline_time TIME, " +
            "priority VARCHAR, " +
            "add_date DATE, " +
            "mod_date DATE, " +
            "group_id INTEGER, " +
            "CONSTRAINT fk_group " +
            "FOREIGN KEY(group_id) " +
            "REFERENCES [group](id) " +
            "ON DELETE CASCADE);"
    ),

    ADD_TASK("INSERT INTO task VALUES (?,?,?,?,?,?,?,?,?,?)"),

    GET_TASK_DUE_TODAY("SELECT t.id, t.name, t.status, t.deadline_date, t.deadline_time, t.priority, g.name FROM task t " +
            "LEFT JOIN [group] g " +
            "ON t.group_id = g.id " +
            "WHERE t.status = 0 " +
            "and t.deadline_date =  date('now') " +
            "ORDER BY t.deadline_time asc;"),


    GET_TASK_SUMMARY("SELECT t.id, t.name, t.status, t.deadline_date, t.deadline_time, t.priority, g.name FROM task t " +
            "LEFT JOIN [group] g " +
            "ON t.group_id = g.id " +
            "ORDER BY t.deadline_date asc " +
            "LIMIT 3;"),


    GET_TASKS("SELECT t.id, t.name, t.description, t.status, t.deadline_date, t.deadline_time, t.priority, g.name FROM task t " +
                    "LEFT JOIN [group] g " +
                    "ON t.group_id = g.id"
    ),

    MODIFY_TASK("UPDATE task SET name = ?, " +
            "description = ?, " +
            "deadline_date = ?, " +
            "deadline_time = ?, " +
            "priority = ?, " +
            "group_id = ?, " +
            "mod_date = ? " +
            "WHERE id = ?;"),

    REMOVE_TASK("DELETE FROM task " +
            "WHERE id = ?;"),


    COMPLETE_TASK("UPDATE task " +
            "SET status = ? " +
            "WHERE id = ?;"),























    READ("SELECT * FROM Task ORDER BY Status DESC"),
    ADD("INSERT INTO Task VALUES (?,?,?,?)");

    private final String query;

    private Queries(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }




}
