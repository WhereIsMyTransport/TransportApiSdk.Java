package transportapisdk.models;

public class Hail {
    private Point geometry;
    
    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }
}
