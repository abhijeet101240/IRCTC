package IRCTC.model;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {

    private String trainId;

    private String trainNo;

    private List<List<Integer>> seats;

    private Map<String, Time> trainStationMap;

    private List<String> station;
}
