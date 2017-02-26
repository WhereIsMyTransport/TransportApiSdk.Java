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
		
		TransportApiResult<List<Agency>> agencies = client.getAgencies(AgencyQueryOptions.defaultQueryOptions());
		
		assertTrue(agencies.isSuccess);
		assertTrue(agencies.data.size() > 0);
	}
	
	@Test
	public void AgenciesNearby_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Agency>> agenciesNearby = client.getAgenciesNearby(AgencyQueryOptions.defaultQueryOptions(), defaultLatitude, defaultLongitude, defaultRadiusInMeters);
		
		assertTrue(agenciesNearby.isSuccess);
		assertTrue(agenciesNearby.data.size() > 0);
	}
	
	@Test
	public void AgenciesByBoundingBox_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<Agency>> agenciesNearby = client.getAgenciesByBoundingBox(AgencyQueryOptions.defaultQueryOptions(), defaultBoundingBox);
		
		assertTrue(agenciesNearby.isSuccess);
		assertTrue(agenciesNearby.data.size() > 0);
	}
	
	@Test
	public void Agency_DefaultValues_IsSuccess() {
		
		TransportApiResult<Agency> agency = client.getAgency(defaultAgencyId);
		
		assertTrue(agency.isSuccess);
		assertTrue(agency.data.getId().equals(defaultAgencyId));
	}

}
