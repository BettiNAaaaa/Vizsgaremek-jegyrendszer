package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(int id, String email, String password, String name, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}