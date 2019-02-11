package transportapisdk;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class Extensions {

	public static boolean isNullOrWhiteSpace(String value) 
	{
	    return value == null || value.trim().isEmpty();
	}
	
	public static String getCurrentDateAndTimeForDifferenceQuery()
	{
        Calendar c = Calendar.getInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        
        return sdf.format(c.getTime());
    }

    public static String getTokenExpiryDate(int secondsInFuture)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, secondsInFuture);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        
        return sdf.format(calendar.getTime());
    }


    public static boolean tokenIsExpired(String inputtingFutureDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        Date testingDate = null;
        Date currentDate = null;
        
        try 
        {
            testingDate = sdf.parse(inputtingFutureDate);
            currentDate = sdf.parse(getCurrentDateAndTimeForDifferenceQuery());
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }

        if (testingDate != null && currentDate != null)
        {
            if(currentDate.after(testingDate))
            {
                return true;
            }
        } 
        else 
        {
            return false;
        }

        return false;
    }
    
    public static String uniqueContextId()
    {	
		try
		{
			//InetAddress []mac = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());

			//return hash(mac);
			
			return "";
		}
		catch(Exception e)
		{
			return "";
		}
    }
    
	private static String hash(InetAddress []mac) 
	{
		long result = 17;
		
		for(int i=0; i < mac.length; i++) 
		{
		  result = 37 * result + mac[i].getHostAddress().hashCode();
		}
		
		return String.valueOf(result);
	}
}
