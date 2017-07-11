package transportapisdk.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class Shortcuts {
	public static Date convertIsoDateTimeStringToDate(String isoDateTime)
    {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try
        {
            return dateFormatter.parse(isoDateTime);
        }
        catch (ParseException pe) {
            return null;
        }
    }
}
