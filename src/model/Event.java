package model;



import java.sql.Timestamp;

public class Event {
    private final int id;
    private final String title;
    private final String room;
    private final String type; 
    private final Timestamp start;
    private final Timestamp end;
    private final int seats;
    private final Integer cinemaId;   
    private final Integer theatreId;  

    public Event(int id, String title, String room, String type,
                 Timestamp start, Timestamp end, int seats,
                 Integer cinemaId, Integer theatreId) {
        this.id = id;
        this.title = title;
        this.room = room;
        this.type = type;
        this.start = start;
        this.end = end;
        this.seats = seats;
        this.cinemaId = cinemaId;
        this.theatreId = theatreId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getRoom() { return room; }
    public String getType() { return type; }
    public Timestamp getStart() { return start; }
    public Timestamp getEnd() { return end; }
    public int getSeats() { return seats; }
    public Integer getCinemaId() { return cinemaId; }
    public Integer getTheatreId() { return theatreId; }
}