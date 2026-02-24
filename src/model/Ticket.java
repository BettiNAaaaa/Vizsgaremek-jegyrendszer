package model;

import java.sql.Timestamp;

public class Ticket {
    private int id;
    private int eventId;
    private String seatLabel;
    private double price;
    private String status;
    private Timestamp created;

    public Ticket(int id, int eventId, String seatLabel, double price, String status, Timestamp created) {
        this.id = id;
        this.eventId = eventId;
        this.seatLabel = seatLabel;
        this.price = price;
        this.status = status;
        this.created = created;
    }

    public int getId() { return id; }
    public int getEventId() { return eventId; }
    public String getSeatLabel() { return seatLabel; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public Timestamp getCreated() { return created; }
}