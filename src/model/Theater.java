/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class Theater {
    private final int id;
    private final String name;
    private final String city;
    private final String address;
    private final String phone;
    private final String website;

    public Theater(int id, String name, String city, String address, String phone, String website) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.website = website;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getWebsite() { return website; }
}