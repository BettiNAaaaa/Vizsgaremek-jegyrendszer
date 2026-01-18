/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class User {
private int id;
private String name;
private String email;


public User(int id, String name, String email) {
this.id = id;
this.name = name;
this.email = email;
}


public String getName() { return name; }
}

