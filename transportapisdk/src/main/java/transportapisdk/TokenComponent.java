package transportapisdk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.json.JSONException;
import org.json.JSONObject;
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
	private final String defaultErrorMessage = "Unable to generate access token. Please check your credentials.";
	
    private String clientId;
    private String clientSecret;
    private String accessToken;
    private String expiryTime;
    
    private static TransportApiResult<String> result = new TransportApiResult<String>();
    
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
    
	public TransportApiResult<String> getAccessToken() 
	{      
		
		
        if (this.accessToken != null)
        {
        	if (!Extensions.tokenIsExpired(expiryTime))
        	{
        		result.isSuccess = true;
        		result.error = "";
        		result.httpStatusCode = 200;
        		result.data = this.accessToken;
        		
        		return result;
        	}
        }
        
        IAuthEndpoint service = GetAuthEndpoint();
        
        Call<User> call = service.GetToken(this.clientId, this.clientSecret, "client_credentials", "transportapi:all");
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
            	result.isSuccess = response.isSuccessful();
            	result.httpStatusCode = response.code();
            	if (response.errorBody() != null){

					try {
						JSONObject jObject = new JSONObject(response.errorBody().string());
						result.error = jObject.getString("error");
					} catch (JSONException e) {
						result.error = defaultErrorMessage;
						e.printStackTrace();
					} catch (IOException e) {
						result.error = defaultErrorMessage;
						e.printStackTrace();
					}
            	}
            	else
            		result.error = "";
        		
                User user = response.body();
                if (user != null)
                {
                	result.data = user.getAccessToken();
                	accessToken = user.getAccessToken();
                    expiryTime = Extensions.getTokenExpiryDate(user.getExpiresIn());
                }
                else
                	result.data = "";

                latch.countDown();
            }

            public void onFailure(Call<User> call, Throwable t) {
            	result.isSuccess = false;
            	result.httpStatusCode = 400;
            	result.data = null;
            	result.error = t.getMessage();
            	
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	result.isSuccess = false;
        	result.httpStatusCode = 400;
        	result.data = null;
        	result.error = "Thread failure, try executing one call at a time.";
        }

        return result;
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
