package ru.zeburek.testngrestassured.types;

public class AuthType {
    public String username;
    public String password;

    public AuthType(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
