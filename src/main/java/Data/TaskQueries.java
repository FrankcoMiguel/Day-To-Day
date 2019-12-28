package Data;

public enum TaskQueries {

    CREATE("CREATE TABLE IF NOT EXISTS Task (Id INTEGER PRIMARY KEY NOT NULL, Name VARCHAR, Deadline TEXT, Status VARCHAR)"),
    READ("SELECT * FROM Task ORDER BY Status DESC"),
    ADD("INSERT INTO Task VALUES (?,?,?,?)");

    private final String query;

    private TaskQueries(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }




}
