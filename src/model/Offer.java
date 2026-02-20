/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joska
 */
public class Offer {
   

    private int id;
    private String text;
    private String validUntil;

    public Offer(int id, String text, String validUntil) {
        this.id = id;
        this.text = text;
        this.validUntil = validUntil;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getValidUntil() {
        return validUntil;
    }
}
