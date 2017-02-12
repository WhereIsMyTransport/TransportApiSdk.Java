package transportapisdk.models;

import java.util.HashMap;
import java.util.Map;

public class LineShape {

    private Stop departureStop;
    private Stop arrivalStop;
    private LineString geometry;
    private Distance distance;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The departureStop
     */
    public Stop getDepartureStop() {
        return departureStop;
    }

    /**
     *
     * @param departureStop
     * The departureStop
     */
    public void setDepartureStop(Stop departureStop) {
        this.departureStop = departureStop;
    }

    /**
     *
     * @return
     * The arrivalStop
     */
    public Stop getArrivalStop() {
        return arrivalStop;
    }

    /**
     *
     * @param arrivalStop
     * The arrivalStop
     */
    public void setArrivalStop(Stop arrivalStop) {
        this.arrivalStop = arrivalStop;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
