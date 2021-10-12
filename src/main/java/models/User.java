package models;

import java.util.UUID;

public class User {
    private String firstName;
    private String surName;
    private String email;
    private String password;

    private UUID uuid;

    public User(String firstName, String surName, String email, String password) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        uuid = UUID.randomUUID();
    }

    public User(String firstName, String surName, String email, String password, UUID uuid) {
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.surName = surName;
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
