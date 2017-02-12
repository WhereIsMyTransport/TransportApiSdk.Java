package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Journey {

    private String id;
    private String href;
    private MultiPoint geometry;
    private String earliestDepartureTime;
    private String latestArrivalTime;
    private int maxItineraries;
    private List<Object> modes = new ArrayList<Object>();
    private List<Object> agencies = new ArrayList<Object>();
    private List<Itinerary> itineraries = new ArrayList<Itinerary>();

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The geometry
     */
    public MultiPoint getGeometry() {
        return geometry;
    }



    /**
     *
     * @return
     * The earliestDepartureTime
     */
    public String getEarliestDepartureTime() {
        return earliestDepartureTime;
    }

    /**
     *
     * @param earliestDepartureTime
     * The earliestDepartureTime
     */
    public void setEarliestDepartureTime(String earliestDepartureTime) {
        this.earliestDepartureTime = earliestDepartureTime;
    }

    /**
     *
     * @return
     * The latestArrivalTime
     */
    public String getLatestArrivalTime() {
        return latestArrivalTime;
    }

    /**
     *
     * @param latestArrivalTime
     * The latestArrivalTime
     */
    public void setLatestArrivalTime(String latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    /**
     *
     * @return
     * The maxItineraries
     */
    public int getMaxItineraries() {
        return maxItineraries;
    }

    /**
     *
     * @param maxItineraries
     * The maxItineraries
     */
    public void setMaxItineraries(int maxItineraries) {
        this.maxItineraries = maxItineraries;
    }

    /**
     *
     * @return
     * The agencies
     */
    public List<Object> getModes() {
        return modes;
    }

    /**
     *
     * @param modes
     * The agencies
     */
    public void setModes(List<Object> modes) {
        this.modes = modes;
    }

    /**
     *
     * @return
     * The agencies
     */
    public List<Object> getAgencies() {
        return agencies;
    }

    /**
     *
     * @param agencies
     * The agencies
     */
    public void setAgencies(List<Object> agencies) {
        this.agencies = agencies;
    }

    /**
     *
     * @return
     * The itineraries
     */
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     *
     * @param itineraries
     * The itineraries
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

}
