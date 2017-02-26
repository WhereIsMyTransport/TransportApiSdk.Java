package transportapisdk;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import transportapisdk.models.Stop;

public class StopTests 
{

private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultBoundingBox = "-33.94,18.36,-33.89,18.43";
	private static double defaultLatitude = -33.986342;  
	private static double defaultLongitude = 18.468806;  
	private static int defaultRadiusInMeters = 5000;
	private static String defaultStopId = "S1twiBqUm0ul6ZMtCnfOcg";
	private static String defaultAgencyId = "A1JHSPIg_kWV5XRHIepCLw";
	
	private static List<String> defaultOnlyAgencies = new ArrayList<String>(Arrays.asList(defaultAgencyId));

	@Test
	public void Stops_DefaultValues_IsSuccess() {
		
		List<Stop> stops = client.GetStops(StopQueryOptions.Default());
		
		assertTrue(stops.size() > 0);
	}
	
	@Test
	public void StopsNearby_DefaultValues_IsSuccess() {
		
		List<Stop> stopNearby = client.GetStopsNearby(StopQueryOptions.Default(), defaultLatitude, defaultLongitude, defaultRadiusInMeters);
		
		assertTrue(stopNearby.size() > 0);
	}
	
	@Test
	public void StopsByBoundingBox_DefaultValues_IsSuccess() {
		
		List<Stop> stopNearby = client.GetStopsByBoundingBox(StopQueryOptions.Default(), defaultBoundingBox);
		
		assertTrue(stopNearby.size() > 0);
	}
	
	@Test
	public void Stop_DefaultValues_IsSuccess() {
		
		Stop stop = client.GetStop(defaultStopId);
		
		assertTrue(stop.getId().equals(defaultStopId));
	}
	
	@Test
	public void Stops_OnlyAgency_IsSuccess() {
		
		StopQueryOptions options = new StopQueryOptions(
				defaultOnlyAgencies, 
				StopQueryOptions.defaultAgencies, 
				StopQueryOptions.defaultModes, 
				StopQueryOptions.defaultModes, 
				StopQueryOptions.defaultServesLines, 
				StopQueryOptions.defaultShowChildren, 
				StopQueryOptions.defaultLimit, 
				StopQueryOptions.defaultOffset, 
				StopQueryOptions.defaultExclude);
		
		List<Stop> stops = client.GetStops(options);
		
		assertTrue(stops.size() > 0);
		for (Stop stop : stops) 
		{
			assertTrue(stop.getAgency().getId().equals(defaultAgencyId));
		} 
	}

}
