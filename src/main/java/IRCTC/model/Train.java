package IRCTC.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.util.List;
import java.util.Map;
@Data
@ToString
@EqualsAndHashCode
public class Train {

    private String trainId;

    private String trainNo;

    private List<List<Integer>> seats;

    private Map<String, String> stationTimes;

    private List<String> station;


    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, String> getTrainStationMap() {
        return stationTimes;
    }

    public void setTrainStationMap(Map<String, String> trainStationMap) {
        this.stationTimes = trainStationMap;
    }

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }

    public Train() {
    }

    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> station) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.station = station;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId='" + trainId + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", seats=" + seats +
                ", trainStationMap=" + stationTimes +
                ", station=" + station +
                '}';
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId,trainNo);
    }
}
