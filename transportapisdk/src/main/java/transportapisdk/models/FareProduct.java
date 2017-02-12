
package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;


public class FareProduct {

    private String id;
    private String href;
    private String name;
    private boolean isDefault;
    private String description;
    private List<Object> restrictions = new ArrayList<Object>();

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
     *     The isDefault
     */
    public boolean isIsDefault() {
        return isDefault;
    }

    /**
     * 
     * @param isDefault
     *     The isDefault
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The restrictions
     */
    public List<Object> getRestrictions() {
        return restrictions;
    }

    /**
     * 
     * @param restrictions
     *     The restrictions
     */
    public void setRestrictions(List<Object> restrictions) {
        this.restrictions = restrictions;
    }

}
