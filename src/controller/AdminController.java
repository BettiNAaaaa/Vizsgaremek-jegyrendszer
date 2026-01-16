/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.MovieService;
import service.OfferService;

public class AdminController {

    private final MovieService movieService = new MovieService();
    private final OfferService offerService = new OfferService();

    public boolean addMovie(String title, String desc) {
        return movieService.createMovie(title, desc);
    }

    public boolean updateDailyOffer(String text, String until) {
        return offerService.updateOffer(text, until);
    }
}