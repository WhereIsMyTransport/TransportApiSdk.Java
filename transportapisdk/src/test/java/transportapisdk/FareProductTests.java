package transportapisdk;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import transportapisdk.models.FareProduct;

public class FareProductTests {

private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultFareProductId = "BQWEZcffgUGF52ah5E9kJQ";

	@Test
	public void FareProducts_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<FareProduct>> fareProducts = client.getFareProducts(FareProductQueryOptions.defaultQueryOptions());
		
		assertTrue(fareProducts.isSuccess);
		assertTrue(fareProducts.data.size() > 0);
	}

	@Test
	public void FareProduct_DefaultValues_IsSuccess() {
		
		TransportApiResult<FareProduct> fareProduct = client.getFareProduct(defaultFareProductId);
		
		assertTrue(fareProduct.isSuccess);
		assertTrue(fareProduct.data.getId().equals(defaultFareProductId));
	}
}
