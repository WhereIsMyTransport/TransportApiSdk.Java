package transportapisdk;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import transportapisdk.models.Itinerary;
import transportapisdk.models.Journey;

public class JourneyTests 
{

	private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static double defaultStartLatitude = -33.986342;  
	private static double defaultStartLongitude = 18.468806;  
	private static double defaultEndLatitude = -33.918842;  
	private static double defaultEndLongitude = 18.389256;
	
	private static String defaultOmitMode = "Bus";
	private static String defaultNoExcludes = "";
	private static String defaultPreviouslyRequestedJourneyId = "TMlaXft1-0qX1acmAJKDnA";
	private static String defaultPrevioulsyRequestedItineraryId = "IO5_2h30eU-Ij6cmAJKFPg";
	
	private static List<String> defaultOmitedModes = new ArrayList<String>(Arrays.asList(defaultOmitMode));
	
	
	@Test
	public void PostJourney_DefaultValues_IsSuccess() 
	{
		Journey journey = client.PostJourney(null, defaultStartLatitude, defaultStartLongitude, defaultEndLatitude, defaultEndLongitude, defaultNoExcludes);
		
		assertTrue(journey.getId() != null);
	}
	
	@Test
	public void PostJourney_OmitBus_IsSuccess() 
	{
		JourneyBodyOptions options = new JourneyBodyOptions(
				null, 
				null, 
				null, 
				defaultOmitedModes, 
				null,
				null);
		
		Journey journey = client.PostJourney(options, defaultStartLatitude, defaultStartLongitude, defaultEndLatitude, defaultEndLongitude, defaultNoExcludes);
		
		assertTrue(journey.getId() != null);
		assertTrue(journey.getOmit().getModes().contains(defaultOmitMode));
	}
	
	@Test
	public void GetJourney_DefaultValues_IsSuccess() 
	{
		Journey journey = client.GetJourney(defaultPreviouslyRequestedJourneyId, defaultNoExcludes);
		
		assertTrue(journey.getId().equals(defaultPreviouslyRequestedJourneyId));
	}
	
	@Test
	public void GetItinerary_DefaultValues_IsSuccess() 
	{
		Itinerary itinerary = client.GetItinerary(defaultPreviouslyRequestedJourneyId, defaultPrevioulsyRequestedItineraryId, defaultNoExcludes);
		
		assertTrue(itinerary.getId().equals(defaultPrevioulsyRequestedItineraryId));
	}

}
