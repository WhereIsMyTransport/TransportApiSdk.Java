package transportapisdk.models;

public class Agency {

    private String id;
    private String href;
    private String name;
    private String culture;
    private String description;

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
     * The culture
     */
    public String getCulture() {
        return culture;
    }

    /**
     *
     * @param culture
     * The culture
     */
    public void setCulture(String culture) {
        this.culture = culture;
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

}