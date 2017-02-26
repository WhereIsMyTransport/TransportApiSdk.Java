package transportapisdk;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import transportapisdk.models.Agency;

public class AgencyTests {
	
	private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultBoundingBox = "-33.94,18.36,-33.89,18.43";
	private static double defaultLatitude = -33.986342;  
	private static double defaultLongitude = 18.468806;  
	private static int defaultRadiusInMeters = 5000;
	private static String defaultAgencyId = "A1JHSPIg_kWV5XRHIepCLw";

	@Test
	public void Agencies_DefaultValues_IsSuccess() {
		
		List<Agency> agencies = client.GetAgencies(AgencyQueryOptions.Default());
		
		assertTrue(agencies.size() > 0);
	}
	
	@Test
	public void AgenciesNearby_DefaultValues_IsSuccess() {
		
		List<Agency> agenciesNearby = client.GetAgenciesNearby(AgencyQueryOptions.Default(), defaultLatitude, defaultLongitude, defaultRadiusInMeters);
		
		assertTrue(agenciesNearby.size() > 0);
	}
	
	@Test
	public void AgenciesByBoundingBox_DefaultValues_IsSuccess() {
		
		List<Agency> agenciesNearby = client.GetAgenciesByBoundingBox(AgencyQueryOptions.Default(), defaultBoundingBox);
		
		assertTrue(agenciesNearby.size() > 0);
	}
	
	@Test
	public void Agency_DefaultValues_IsSuccess() {
		
		Agency agency = client.GetAgency(defaultAgencyId);
		
		assertTrue(agency.getId().equals(defaultAgencyId));
	}

}
