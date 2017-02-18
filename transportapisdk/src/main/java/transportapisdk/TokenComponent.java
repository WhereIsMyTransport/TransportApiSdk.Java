package transportapisdk;

import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import transportapisdk.models.User;

interface IAuthEndpoint{
    @FormUrlEncoded
    @POST("connect/token")
    Call<User> GetToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType, @Field("scope") String scope);
}

class TokenComponent 
{
	private static IAuthEndpoint authEndpoint;
	
	private final CountDownLatch latch = new CountDownLatch(1);
	
    private String clientId;
    private String clientSecret;
    private String accessToken;
    private String expiryTime;
    
    public TokenComponent(String clientId, String clientSecret)
    {
    	if (Extensions.isNullOrWhiteSpace(clientId))
    	{
    		throw new IllegalArgumentException("ClientId cannot be null.");
    	}
    	
    	if (Extensions.isNullOrWhiteSpace(clientSecret))
    	{
    		throw new IllegalArgumentException("ClientSecret cannot be null.");
    	}
    	
    	this.clientId = clientId;
    	this.clientSecret = clientSecret;
    }
    
	public String GetAccessToken() 
	{      
        if (this.accessToken != null)
        {
        	if (!Extensions.tokenIsExpired(expiryTime))
        	{
        		return this.accessToken;
        	}
        }
        
        IAuthEndpoint service = GetAuthEndpoint();
        
        Call<User> call = service.GetToken(this.clientId, this.clientSecret, "client_credentials", "transportapi:all");
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                accessToken = user.getAccessToken();
                expiryTime = Extensions.getTokenExpiryDate(user.getExpiresIn());
                
                latch.countDown();
            }

            public void onFailure(Call<User> call, Throwable t) {
                System.out.print("TODO - Failed on GetAccessToken");
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	System.out.print("TODO - Failed during latch await.");
        }

        return accessToken;
    }
    
    private static IAuthEndpoint GetAuthEndpoint()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        
        if(authEndpoint == null)
        {
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("https://identity.whereismytransport.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            authEndpoint = restAdapter.create(IAuthEndpoint.class);
        }
        
        return authEndpoint;
    }
}
