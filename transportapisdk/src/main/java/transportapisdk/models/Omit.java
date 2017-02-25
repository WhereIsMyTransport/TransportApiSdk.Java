package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Omit 
{
	private List<String> agencies = new ArrayList<String>();
	private List<String> modes = new ArrayList<String>();
	
	/**
    *
    * @return
    * The agencies omitted from the call.
    */
   public List<String> getAgencies() {
       return agencies;
   }

   /**
    *
    * @param agencies
    * The agencies omitted from the call.
    */
   public void setAgencies(List<String> agencies) {
       this.agencies = agencies;
   }
   
   /**
   *
   * @return
   * The modes omitted from the call.
   */
  public List<String> getModes() {
      return modes;
  }

  /**
   *
   * @param modes
   * The modes omitted from the call.
   */
  public void setModes(List<String> modes) {
      this.modes = modes;
  }
}
