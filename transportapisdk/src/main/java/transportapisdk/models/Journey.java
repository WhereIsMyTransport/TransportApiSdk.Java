package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Journey {

    private String id;
    private String href;
    private MultiPoint geometry;
    private String time;
    private TimeType timeType;
    private Profile profile;
    private Only only;
    private Omit omit;
    private int maxItineraries;
    private List<String> fareProducts = new ArrayList<String>();
    private List<Itinerary> itineraries = new ArrayList<Itinerary>();

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
    * The time
    */
   public String getTime() {
       return time;
   }

   /**
    *
    * @param time
    * The time
    */
   public void setTime(String time) {
       this.time = time;
   }
   
   /**
   *
   * @return
   * The timeType
   */
  public TimeType getTimeType() {
      return timeType;
  }

  /**
   *
   * @param time
   * The time
   */
  public void setTimeType(TimeType timeType) {
      this.timeType = timeType;
  }
  
  /**
  *
  * @return
  * The profile
  */
 public Profile getProfile() {
     return profile;
 }

 /**
  *
  * @param profile
  * The profile
  */
 public void setProfile(Profile profile) {
     this.profile = profile;
 }

    /**
     *
     * @return
     * The geometry
     */
    public MultiPoint getGeometry() {
        return geometry;
    }
    
    /**
    *
    * @param geometry
    * The geometry
    */
   public void setGeometry(MultiPoint geometry) {
       this.geometry = geometry;
   }

    /**
     *
     * @return
     * The maxItineraries
     */
    public int getMaxItineraries() {
        return maxItineraries;
    }

    /**
     *
     * @param maxItineraries
     * The maxItineraries
     */
    public void setMaxItineraries(int maxItineraries) {
        this.maxItineraries = maxItineraries;
    }

    /**
     *
     * @return
     * The fareProducts
     */
    public List<String> getFareProducts() {
        return fareProducts;
    }

    /**
     *
     * @param fareProducts
     * The fareProducts
     */
    public void setFareProducts(List<String> fareProducts) {
        this.fareProducts = fareProducts;
    }

    /**
     *
     * @return
     * The itineraries
     */
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     *
     * @param itineraries
     * The itineraries
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
    
    /**
    *
    * @return
    * The only objects used in the call.
    */
   public Only getOnly() {
       return only;
   }

   /**
    *
    * @param only
    * The only objects used in the call.
    */
   public void setOnly(Only only) {
       this.only = only;
   }
   
   /**
   *
   * @return
   * The objects omitted from the call.
   */
  public Omit getOmit() {
      return omit;
  }

  /**
   *
   * @param only
   * The objects omitted from the call.
   */
  public void setOmit(Omit omit) {
      this.omit = omit;
  }

}
