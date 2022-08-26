package ob.assignments.interproccommunication.model;

/**
 * Client entity
 */
public class Client {

    private long id;
    private String fullname;

    public Client(long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
