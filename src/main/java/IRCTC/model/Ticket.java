package IRCTC.model;


import lombok.*;
import org.springframework.ws.transport.TransportInputStream;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class Ticket {

    private String ticketId;

    private String userId;

    private String source;

    private String destination;

    private String dateOfTravelling;

    private Train train;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOfTravelling() {
        return dateOfTravelling;
    }

    public void setDateOfTravelling(String dateOfTravelling) {
        this.dateOfTravelling = dateOfTravelling;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Ticket() {
    }

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravelling, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravelling = dateOfTravelling;
        this.train = train;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", dateOfTravelling=" + dateOfTravelling +
                ", train=" + train +
                '}';
    }

    public String getTicketInfo(){
        return String.format("Train ID: %s Train No: %s", ticketId, userId, source, destination, dateOfTravelling);
    }
}
