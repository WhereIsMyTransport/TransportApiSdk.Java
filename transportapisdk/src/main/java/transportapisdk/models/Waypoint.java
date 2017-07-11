package transportapisdk.models;

import java.util.Date;

public class Waypoint {

    private Stop stop;
    private String arrivalTime;
    private String departureTime;
    private Location location;
    private Hail hail;
    private String pickupType;
    private String dropOffType;

    /**
     * 
     * @return
     *     The stop
     */
    public Stop getStop() {
        return stop;
    }

    /**
     * 
     * @param stop
     *     The stop
     */
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    /**
    *
    * @return
    * The arrivalTime
    */
   public Date getArrivalTimeAsDate() {
       return Shortcuts.convertIsoDateTimeStringToDate(arrivalTime);
   }

    /**
     * 
     * @param arrivalTime
     *     The arrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }
    
    /**
    *
    * @return
    * The departureTime
    */
   public Date getDepartureTimeAsDate() {
       return Shortcuts.convertIsoDateTimeStringToDate(departureTime);
   }

    /**
     * 
     * @param departureTime
     *     The departureTime
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    /**
     * 
     * @return
     *     The pickupType
     */
    public String getPickupType() {
        return pickupType;
    }

    /**
     * 
     * @param pickupType
     *     The pickupType
     */
    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }
    
    /**
     * 
     * @return
     *     The dropOffType
     */
    public String getDropOffType() {
        return dropOffType;
    }

    /**
     * 
     * @param dropOffType
     *     The dropOffType
     */
    public void setDropOffType(String dropOffType) {
        this.dropOffType = dropOffType;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * 
     * @return
     *     The hail
     */
    public Hail getHail() {
        return hail;
    }

    /**
     * 
     * @param hail
     *     The hail
     */
    public void setHail(Hail hail) {
        this.hail = hail;
    }

}
