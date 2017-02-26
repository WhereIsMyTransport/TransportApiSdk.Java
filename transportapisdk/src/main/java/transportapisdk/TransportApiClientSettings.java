package transportapisdk;

public class TransportApiClientSettings 
{
	public String clientId;
	public String clientSecret;
	public int timeoutInSeconds;
    
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
    	
    	this.timeoutInSeconds = 30;
    	this.clientId = clientId;
    	this.clientSecret = clientSecret;
    }
    
    public TransportApiClientSettings(String clientId, String clientSecret, int timeoutInSeconds)
    {
    	this(clientId, clientSecret);
    	
    	if (timeoutInSeconds <= 0 || timeoutInSeconds > 60)
    	{
    		throw new IllegalArgumentException("TimeoutInSeconds must be between 0 and 61.");
    	}
    	
    	this.timeoutInSeconds = timeoutInSeconds;
    }
}
