package model;



import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ticket {
    private final int id;
    private final int eventId;
    private final String seatLabel;
    private final BigDecimal price;
    private final String status;
    private final Timestamp created;

    public Ticket(int id, int eventId, String seatLabel, BigDecimal price, String status, Timestamp created) {
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
    public BigDecimal getPrice() { return price; }
    public String getStatus() { return status; }
    public Timestamp getCreated() { return created; }
}