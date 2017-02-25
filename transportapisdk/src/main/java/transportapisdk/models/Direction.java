package transportapisdk.models;

public class Direction {

    private String instruction;
    private Distance distance;

    /**
     * 
     * @return
     *     The instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * 
     * @param instruction
     *     The instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

}
