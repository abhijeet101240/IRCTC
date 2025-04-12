package IRCTC.services;

import IRCTC.model.Train;
import IRCTC.model.User;
import IRCTC.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
        File file = new File(USER_PATH);

        if (file.exists() && file.length() > 0) {
            userList = objectMapper.readValue(new File(USER_PATH), new TypeReference<List<User>>() {
            });
        }else {
            userList = new ArrayList<>();
        }
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

    public  List<List<Integer>> fetchSeats(Train train){
        if (train == null){
            System.out.println("train is null");
            return null;
        }

        List<List<Integer>> seats = train.getSeats();

        if (seats == null){
            System.out.println("Seats in train" + train.getTrainId() + " are null");
            return null;
        }
        return seats;
    }

    public Boolean bookTrainSeats(Train train, int row, int seat){

        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();

            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()){
                if (seats.get(row).get(seat) == 0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);

                    trainService.addTrains(train);
                    return true;

                }else {
                    return false;
                }
            }else {
                return false;
            }

        }catch (IOException e){
                return Boolean.FALSE;
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

   public List<Train> getTrain(String source, String Destination){

        try {
            TrainService trainService = new TrainService();
            trainService.searchTrains(source,Destination);
        }catch (IOException ae){
            ae.printStackTrace();
        }
       return new ArrayList<>();
    }

}