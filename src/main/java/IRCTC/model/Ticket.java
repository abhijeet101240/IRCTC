package IRCTC.model;


import org.springframework.ws.transport.TransportInputStream;

import java.util.Date;

public class Ticket {

    private String ticketId;

    private String userId;

    private String source;

    private String destination;

    private Date dateOfTravelling;

    private Train train;
}
