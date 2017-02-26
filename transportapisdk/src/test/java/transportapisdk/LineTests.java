package transportapisdk;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import transportapisdk.models.Line;

public class LineTests 
{

	private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultBoundingBox = "-33.94,18.36,-33.89,18.43";
	private static double defaultLatitude = -33.986342;  
	private static double defaultLongitude = 18.468806;  
	private static int defaultRadiusInMeters = 5000;
	private static String defaultLineId = "giwBPOBfeE-C4acZAI_7uQ";
	private static String defaultAgencyId = "A1JHSPIg_kWV5XRHIepCLw";
	
	private static List<String> defaultOnlyAgencies = new ArrayList<String>(Arrays.asList(defaultAgencyId));

	@Test
	public void Lines_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Line>> lines = client.getLines(LineQueryOptions.defaultQueryOptions());
		
		assertTrue(lines.isSuccess);
		assertTrue(lines.data.size() > 0);
	}
	
	@Test
	public void LinesNearby_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Line>> lineNearby = client.getLinesNearby(LineQueryOptions.defaultQueryOptions(), defaultLatitude, defaultLongitude, defaultRadiusInMeters);
		
		assertTrue(lineNearby.isSuccess);
		assertTrue(lineNearby.data.size() > 0);
	}
	
	@Test
	public void LinesByBoundingBox_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Line>> lineNearby = client.getLinesByBoundingBox(LineQueryOptions.defaultQueryOptions(), defaultBoundingBox);
		
		assertTrue(lineNearby.isSuccess);
		assertTrue(lineNearby.data.size() > 0);
	}
	
	@Test
	public void Line_DefaultValues_IsSuccess() {
		
		TransportApiResult<Line> line = client.getLine(defaultLineId);
		
		assertTrue(line.isSuccess);
		assertTrue(line.data.getId().equals(defaultLineId));
	}
	
	@Test
	public void Lines_OnlyAgency_IsSuccess() {
		
		LineQueryOptions options = new LineQueryOptions(
				defaultOnlyAgencies, 
				LineQueryOptions.defaultAgencies, 
				LineQueryOptions.defaultModes, 
				LineQueryOptions.defaultModes, 
				LineQueryOptions.defaultServesStops, 
				LineQueryOptions.defaultLimit, 
				LineQueryOptions.defaultOffset, 
				LineQueryOptions.defaultExclude);
		
		TransportApiResult<List<Line>> lines = client.getLines(options);
		
		assertTrue(lines.isSuccess);
		assertTrue(lines.data.size() > 0);
		for (Line line : lines.data) 
		{
			assertTrue(line.getAgency().getId().equals(defaultAgencyId));
		} 
	}

}
