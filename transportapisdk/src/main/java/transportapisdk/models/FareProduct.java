
package transportapisdk.models;

public class FareProduct {

    private String id;
    private String href;
    private String name;
    private boolean isDefault;
    private String description;
    private Agency agency;

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

}
