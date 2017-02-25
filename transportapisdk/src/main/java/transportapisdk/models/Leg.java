package transportapisdk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leg {

    private String type;
    private String behaviour;
    private Distance distance;
    private int duration;
    private Line line;
    private Vehicle vehicle;
    private Fare fare;
    private List<Waypoint> waypoints = new ArrayList<Waypoint>();
    private List<Direction> directions = new ArrayList<Direction>();
    private LineString geometry;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
    *
    * @return
    * The behaviour
    */
   public String getBehaviour() {
       return behaviour;
   }

   /**
    *
    * @param behaviour
    * The behaviour
    */
   public void setBehaviour(String behaviour) {
       this.behaviour = behaviour;
   }

    /**
     *
     * @return
     * The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     * The duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The waypoints
     */
    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    /**
     *
     * @param waypoints
     * The waypoints
     */
    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    /**
     *
     * @return
     * The directions
     */
    public List<Direction> getDirections() {
        return directions;
    }

    /**
     *
     * @param directions
     * The directions
     */
    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    /**
     *
     * @return
     * The geometry
     */
    public LineString getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(LineString geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The line
     */
    public Line getLine() {
        return line;
    }

    /**
     *
     * @param line
     * The line
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     *
     * @return
     * The vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     *
     * @param vehicle
     * The vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     *
     * @return
     * The fare
     */
    public Fare getFare() {
        return fare;
    }

    /**
     *
     * @param fare
     * The fare
     */
    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
