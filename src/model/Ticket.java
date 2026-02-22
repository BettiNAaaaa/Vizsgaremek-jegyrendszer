package model;

public class Ticket {

    private int id;
    private int userId;
    private int eventId;
    private int seatCount;

    public Ticket(int id, int userId, int eventId, int seatCount) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.seatCount = seatCount;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getEventId() { return eventId; }
    public int getSeatCount() { return seatCount; }
}