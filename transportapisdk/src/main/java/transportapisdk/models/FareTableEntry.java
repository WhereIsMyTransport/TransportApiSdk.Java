package transportapisdk.models;

public class FareTableEntry {

    private DepartureZone departureZone;
    private ArrivalZone arrivalZone;
    private Cost cost;

    /**
     *
     * @return
     * The departureZone
     */
    public DepartureZone getDepartureZone() {
        return departureZone;
    }

    /**
     *
     * @param departureZone
     * The departureZone
     */
    public void setDepartureZone(DepartureZone departureZone) {
        this.departureZone = departureZone;
    }

    /**
     *
     * @return
     * The arrivalZone
     */
    public ArrivalZone getArrivalZone() {
        return arrivalZone;
    }

    /**
     *
     * @param arrivalZone
     * The arrivalZone
     */
    public void setArrivalZone(ArrivalZone arrivalZone) {
        this.arrivalZone = arrivalZone;
    }

    /**
     *
     * @return
     * The cost
     */
    public Cost getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     * The cost
     */
    public void setCost(Cost cost) {
        this.cost = cost;
    }

}
