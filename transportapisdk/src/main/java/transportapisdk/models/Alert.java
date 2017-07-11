package transportapisdk.models;

public class Alert {
	private String message;
	private String effect;
	
	/**
    *
    * @return
    * The message
    */
   public String getMessage() {
       return message;
   }

   /**
    *
    * @param message
    * The message
    */
   public void setMessage(String message) {
       this.message = message;
   }

   /**
    *
    * @return
    * The effect
    */
   public String getEffect() {
       return effect;
   }

   /**
    *
    * @param effect
    * The effect
    */
   public void setEffect(String effect) {
       this.effect = effect;
   }
}
