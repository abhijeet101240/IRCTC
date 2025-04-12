package IRCTC.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {

    private String train_id;

    private String trainNo;

    @JsonProperty("seats")
    private List<List<Integer>> seats;

    private Map<String, String> stationTimes;

    @JsonProperty("stations")
    private List<String> station;


    public String getTrainId() {
        return train_id;
    }

    public void setTrainId(String trainId) {
        this.train_id = trainId;
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

    public Map<String, String> getTrainStationTimes() {
        return stationTimes;
    }

    public void setTrainStationTimes(Map<String, String> trainStationMap) {
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
        this.train_id = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.station = station;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId='" + train_id + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", seats=" + seats +
                ", trainStationMap=" + stationTimes +
                ", station=" + station +
                '}';
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", train_id,trainNo);
    }
}
