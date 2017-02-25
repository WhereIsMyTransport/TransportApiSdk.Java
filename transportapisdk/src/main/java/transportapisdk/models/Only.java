package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Only 
{
	private List<String> agencies = new ArrayList<String>();
	private List<String> modes = new ArrayList<String>();
	
	/**
    *
    * @return
    * The only agencies used in the call.
    */
   public List<String> getAgencies() {
       return agencies;
   }

   /**
    *
    * @param agencies
    * The only agencies used in the call.
    */
   public void setAgencies(List<String> agencies) {
       this.agencies = agencies;
   }
   
   /**
   *
   * @return
   * The only modes used in the call.
   */
  public List<String> getModes() {
      return modes;
  }

  /**
   *
   * @param modes
   * The only modes used in the call.
   */
  public void setModes(List<String> modes) {
      this.modes = modes;
  }
}
