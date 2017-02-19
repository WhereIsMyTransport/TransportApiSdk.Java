package transportapisdk;

import java.util.List;

import transportapisdk.models.Agency;

public class Test {

	public static void main(String[] args) {

		String clientId = "CLIENT_ID";
	    String clientSecret = "CLIENT_SECRET";
	    
		TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(clientId, clientSecret));
		
		List<Agency> agencies = client.GetAgencies(AgencyOptions.Default());
		
		List<Agency> agenciesNearby = client.GetAgenciesNearby(AgencyOptions.Default(), -33.986342, 18.468806, 5000);
	}

}
