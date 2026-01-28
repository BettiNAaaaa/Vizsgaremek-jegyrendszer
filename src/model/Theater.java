/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;




public class Theater extends Event {

    /**
     * Színházi előadás létrehozása
     *
     * @param id az előadás azonosítója
     * @param title az előadás címe
     * @param availableSeats elérhető helyek száma
     */
    public Theater(int id, String title, int availableSeats) {
        super(id, title, availableSeats);
    }
}
