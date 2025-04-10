package IRCTC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@JsonNaming(PropertyNamingStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
/*//These two annotations are from the Jackson library in Java and are commonly used for mapping
JSON to Java objects and vice versa. Here's a simple explanation of each:*/

public class User {

    private String name;

    private String password;

    private String hashedPassword;

    private List<Ticket> ticketsBooked;

    private String userID;

    public User() {
    }

    public User(String name, String userID, List<Ticket> ticketsBooked, String hashedPassword, String password) {
        this.name = name;
        this.userID = userID;
        this.ticketsBooked = ticketsBooked;
        this.hashedPassword = hashedPassword;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", ticketsBooked=" + ticketsBooked +
                ", userID='" + userID + '\'' +
                '}';
    }

   public void printTicket(){

        for (int i = 0 ; i<ticketsBooked.size();i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }
}
