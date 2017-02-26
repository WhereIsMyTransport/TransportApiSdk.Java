package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class LineTimetable {

    private Vehicle vehicle;
    private List<Waypoint> waypoints = new ArrayList<Waypoint>();

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
}
