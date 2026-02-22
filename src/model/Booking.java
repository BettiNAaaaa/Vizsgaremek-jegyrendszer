package model;

public class Booking {
    private int id; // ticket id (ha van)
    private User user;
    private Event event;
    private int seatCount;

    public Booking(int id, User user, Event event, int seatCount) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.seatCount = seatCount;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public Event getEvent() { return event; }
    public int getSeatCount() { return seatCount; }

    public String getSummary() {
        return user.getName() + " foglalt " + seatCount + " jegyet erre: " + event.getTitle();
    }
}