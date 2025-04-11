package IRCTC.services;

import IRCTC.model.Train;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TrainService {



    private List<Train> trainList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final static String  USER_PATH = "src/main/java/IRCTC/localDB/trains.json";





}
