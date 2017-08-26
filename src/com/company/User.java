package com.company;

public class User {
    /*
        Variables
    */
    private String IP;
    private String username;
    private int UserID;

    /*
        Constructors
    */
    public User(int UserID){
        this.UserID = UserID;
    }

    public User(int UserID, String username, String IP){
        this.UserID = UserID;
        this.username = username;
        this.IP = IP;
    }

    /*
        Functions
    */
    public void setUsername(String username){this.username = username;}
    public void setIP(String IP){this.IP = IP;}
    public String getUsername(){return username;}
    public String getIP(){return IP;}
    public int getUserID(){return UserID;}
}
