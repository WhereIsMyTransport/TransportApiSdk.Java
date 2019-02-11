package transportapisdk;

public class TransportApiClientSettings 
{
	public String clientId;
	public String clientSecret;
	public String uniqueContextId;
	public String environmentUrl;
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
    	this.uniqueContextId = java.util.UUID.randomUUID().toString();
    	this.environmentUrl = "https://platform.whereismytransport.com/api/"; 
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
    
    public TransportApiClientSettings(String clientId, String clientSecret, int timeoutInSeconds, String uniqueContextId)
    {
    	this(clientId, clientSecret, timeoutInSeconds);
    	
    	if (Extensions.isNullOrWhiteSpace(uniqueContextId))
    	{
    		throw new IllegalArgumentException("UniqueContextId cannot be null.");
    	}
    	
    	this.uniqueContextId = uniqueContextId;
    }
    
    public TransportApiClientSettings(String clientId, String clientSecret, int timeoutInSeconds, String uniqueContextId, String environmentUrl)
    {
    	this(clientId, clientSecret, timeoutInSeconds, uniqueContextId);
    	
    	if (Extensions.isNullOrWhiteSpace(environmentUrl))
    	{
    		throw new IllegalArgumentException("EnvironmentUrl cannot be null.");
    	}
    	
    	this.environmentUrl = environmentUrl;
    }
}
