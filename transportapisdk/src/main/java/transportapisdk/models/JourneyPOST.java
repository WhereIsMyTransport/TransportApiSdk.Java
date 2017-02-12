package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JourneyPOST {

    private MultiPoint geometry;
    private String earliestDepartureTime;
    private String latestArrivalTime;
    private int maxItineraries;
    private List<String> modes = new ArrayList<String>();
    private List<String> agencies = new ArrayList<String>();

    public JourneyPOST(MultiPoint geometry,String earliestDepartureTime,String latestArrivalTime,int max,List<String> modes,List<String> agencies){
        this.geometry = geometry;
        this.earliestDepartureTime = earliestDepartureTime;
        this.latestArrivalTime = latestArrivalTime;
        this.maxItineraries = max;
        this.modes = modes;
        this.agencies = agencies;
    }

}

