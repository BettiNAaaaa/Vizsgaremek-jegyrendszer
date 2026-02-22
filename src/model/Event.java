package model;

public class Event {
    private int id;
    private String title;
    private String type; // film v darab
    private int availableSeats;
    private String posterUrl; // lehet üres

    public Event(int id, String title, String type, int availableSeats, String posterUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.availableSeats = availableSeats;
        this.posterUrl = posterUrl;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public int getAvailableSeats() { return availableSeats; }
    public String getPosterUrl() { return posterUrl; }

    public void bookSeat(int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seat count must be positive.");
        }
        if (availableSeats < seats) {
            throw new IllegalStateException("Not enough available seats.");
        }
        availableSeats -= seats;
    }
}