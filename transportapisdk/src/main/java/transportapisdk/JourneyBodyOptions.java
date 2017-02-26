package transportapisdk;

import java.util.List;
import transportapisdk.models.Profile;
import transportapisdk.models.TimeType;

public class JourneyBodyOptions
{
	private final static List<String> defaultModes = null;
	private final static List<String> defaultAgencies = null;
	private final static List<String> defaultFareProducts = null;
	private final static int defaultMaxItineraries = 3;
	
	public List<String> onlyAgencies = null;
	public List<String> omitAgencies = null;
	public List<String> onlyModes = null;
	public List<String> omitModes = null;
	public List<String> fareProducts = null;
	public String time;
	public TimeType timeType;
	public Profile profile;
	public int maxItineraries;
	
	public JourneyBodyOptions(
			List<String> onlyAgencies, 
			List<String> omitAgencies,  
			List<String> onlyModes, 
			List<String> omitModes, 
			Integer maxItineraries,
			List<String> fareProducts) 
	{	
		if (onlyModes != null & omitModes != null)
		{
			throw new IllegalArgumentException("Either onlyModes or omitModes can be provided. Not both.");
		}
		
		if (onlyAgencies != null & omitAgencies != null)
		{
			throw new IllegalArgumentException("Either onlyAgencies or omitAgencies can be provided. Not both.");
		}
		
		if (maxItineraries != null && (maxItineraries < 1 || maxItineraries > 5))
		{
			throw new IllegalArgumentException("Invalid value for maxItineraries. Expected a value between or including 1 and 5.");
		}
		
		this.fareProducts = fareProducts;
		
		if (onlyAgencies != null)
			this.onlyAgencies = onlyAgencies;
		if (omitAgencies != null)
			this.omitAgencies = omitAgencies;
		
		if (onlyModes != null)
			this.onlyModes = onlyModes;
		if (omitModes != null)
			this.omitModes = omitModes;

		if (maxItineraries != null)
			this.maxItineraries = maxItineraries;
		else
			this.maxItineraries = defaultMaxItineraries;
	}
	
	public static JourneyBodyOptions defaultQueryOptions()
	{
		return new JourneyBodyOptions(defaultAgencies, defaultAgencies, defaultModes, defaultModes, defaultMaxItineraries, defaultFareProducts);
	}
}
