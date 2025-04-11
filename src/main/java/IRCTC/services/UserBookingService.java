package IRCTC.services;

import IRCTC.model.User;
import IRCTC.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH = "src/main/java/IRCTC/localDB/users.json";

    public UserBookingService(User user) throws IOException {

        this.user = user;

        loadUserFromListLD();
    }

    public UserBookingService() throws IOException {

        loadUserFromListLD();
    }

    private void loadUserFromListLD() throws IOException {

        objectMapper.readValue(new File(USER_PATH), new TypeReference<User>() {});
    }


    public boolean loginUser() {

        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();

        return foundUser.isPresent();
    }


    public boolean signUp(User user) {

        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException io) {

            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws  IOException {

        File file = new File(USER_PATH);

        objectMapper.writeValue(file, userList);
    }

    public void fetchTicket(){

        Optional<User> fetchedUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();

        if (fetchedUser.isPresent()){
            fetchedUser.get().printTicket();
        }

    }

    public boolean cancelBooking(String ticketID){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ticket id to Cancel the Ticket");
        ticketID = sc.next();


        if (ticketID == null && ticketID.isEmpty()){
            System.out.println("Ticket ID cannot be null or empty");
            return Boolean.FALSE;
        }

        String finalTicket1 = ticketID;
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicket1));

        String finalTicketID = ticketID;
        user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketID));

        if (removed){
            System.out.println("Ticket ID" + ticketID + "has been cancelled Succesfully");
            return  Boolean.TRUE;

        } else {
            System.out.println( " No record found for " + ticketID);
            return Boolean.FALSE;

        }
    }

   /* public List<List> getTrain(String source, String Destination){

    }*/

}