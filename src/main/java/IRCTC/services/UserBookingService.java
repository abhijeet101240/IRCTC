package IRCTC.services;

import IRCTC.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH = "src/main/java/IRCTC/localDB/users.json";

    public UserBookingService(User user) throws IOException {

        this.user = user;

        File users = new File(USER_PATH);

      userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }


  /* *public boolean loginUser(){

        userList.stream().filter(user1 ->{
            return user1.getName().equals(user.getName())
        } )

    }*/
}
