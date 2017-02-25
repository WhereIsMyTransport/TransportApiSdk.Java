package transportapisdk.models;

public class Line {

    private String id;
    private String href;
    private Agency agency;
    private String name;
    private String shortName;
    private String description;
    private String mode;
    private String colour;
    private String textColour;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The agency
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     *
     * @param agency
     * The agency
     */
    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     * The shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    /**
    *
    * @return
    * The description
    */
   public String getDescription() {
       return description;
   }

   /**
    *
    * @param description
    * The description
    */
   public void setDescription(String description) {
       this.description = description;
   }

    /**
     *
     * @return
     * The mode
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     * The mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     *
     * @return
     * The colour
     */
    public String getColour() {
        return colour;
    }

    /**
     *
     * @param colour
     * The colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     *
     * @return
     * The textColour
     */
    public String getTextColour() {
        return textColour;
    }

    /**
     *
     * @param textColour
     * The textColour
     */
    public void setTextColour(String textColour) {
        this.textColour = textColour;
    }

}