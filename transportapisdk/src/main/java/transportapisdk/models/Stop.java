package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Stop {

    private String id;
    private String href;
    private Agency agency;
    private String name;
    private String code;
    private Point geometry;
    private List<String> modes = new ArrayList<String>();
    private Stop parentStop;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The href
     */
    public String getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 
     * @return
     *     The agency
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     * 
     * @param agency
     *     The agency
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The geometry
     */
    public Point getGeometry() {
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
    
    /**
     * 
     * @return
     *     The modes
     */
    public List<String> getModes() {
        return modes;
    }

    /**
     * 
     * @param modes
     *     The modes
     */
    public void setModes(List<String> modes) {
        this.modes = modes;
    }

    /**
     * 
     * @return
     *     The parentStop
     */
    public Stop getParentStop() {
        return parentStop;
    }

    /**
     * 
     * @param parentStop
     *     The parentStop
     */
    public void setParentStop(Stop parentStop) {
        this.parentStop = parentStop;
    }
}
