package IRCTC.services;

import IRCTC.model.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {



    private List<Train> trainList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final static String  TRAIN_PATH = "src/main/java/IRCTC/localDB/trains.json";

    private TrainService() throws IOException {

        File trains = new File(TRAIN_PATH);

        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});

    }

    public List<Train> searchTrains(String source, String destination){

    return trainList.stream().
            filter(train -> validTrain(train,source,destination)).
            collect(Collectors.toList());

    }

    public void updateTrains(Train updatedTrain){

        OptionalInt index = IntStream.range(0, trainList.size()).
                filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId())).
                findFirst();

        if(index.isPresent()){
            trainList.set(index.getAsInt(),updatedTrain);
            saveTrainInFile();

        }else {
            //if train not found then it will add it ass new train
            addTrains(updatedTrain);
        }

   }

   public void addTrains(Train newTrain){
       Optional<Train> isTrainExist = trainList.stream().filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId())).findFirst();

      if (isTrainExist.isPresent()){
           updateTrains(newTrain);

       }else {
           trainList.add(newTrain);
           saveTrainInFile();
       }

   }

    public void saveTrainInFile(){

        try {

            objectMapper.writeValue(new File(TRAIN_PATH), trainList);
        } catch (IOException e){
            e.printStackTrace();
        }


    }


    public boolean validTrain(Train train,String source , String destination){

        List<String> stationOrder = train.getStation();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;

    }







}
