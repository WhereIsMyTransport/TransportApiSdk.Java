package transportapisdk;

public class TransportApiClientSettings 
{
	public String ClientId;
	public String ClientSecret;
    
    public TransportApiClientSettings(String clientId, String clientSecret)
    {
    	if (Extensions.isNullOrWhiteSpace(clientId))
    	{
    		throw new IllegalArgumentException("ClientId cannot be null.");
    	}
    	
    	if (Extensions.isNullOrWhiteSpace(clientSecret))
    	{
    		throw new IllegalArgumentException("ClientSecret cannot be null.");
    	}
    	
    	this.ClientId = clientId;
    	this.ClientSecret = clientSecret;
    }
}
