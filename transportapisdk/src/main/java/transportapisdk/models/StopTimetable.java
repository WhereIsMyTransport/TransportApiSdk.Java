package transportapisdk.models;

/**
 * Created by James on 2016/07/21.
 */

public class StopTimetable {

    private String arrivalTime;
    private String departureTime;
    private Line line;
    private Vehicle vehicle;

    /**
     *
     * @return
     * The arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     *
     * @param arrivalTime
     * The arrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     *
     * @return
     * The departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     *
     * @param departureTime
     * The departureTime
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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

}
