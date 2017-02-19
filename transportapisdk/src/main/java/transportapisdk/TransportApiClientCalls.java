package transportapisdk;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import transportapisdk.models.Agency;
import transportapisdk.models.Point;

interface ITransportApi
{
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies")
	Call<List<Agency>> GetAgencies(@Query("agencies") List<String> agencies, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox);
}

class TransportApiClientCalls 
{
	private final static CountDownLatch latch = new CountDownLatch(1);
	
	// TODO Not the greatest, but these are all the return values for the call-backs to set.
	private static List<Agency> agencies = null;
    
	public static List<Agency> GetAgencies(final TokenComponent tokenComponent, AgencyOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<List<Agency>> call = service.GetAgencies(options.agencies, options.limit, options.offset, point, radiusInMeters, boundingBox);
        
        call.enqueue(new Callback<List<Agency>>() {
            public void onResponse(Call<List<Agency>> call, Response<List<Agency>> response) {
            	agencies = response.body();

                latch.countDown();
            }

            public void onFailure(Call<List<Agency>> call, Throwable t) {
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

        return agencies;
    }
    
    private static ITransportApi GetTransportApiClient(final TokenComponent tokenComponent)
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Interceptor headerIntercept = new Interceptor()
        {
            public okhttp3.Response intercept(Chain chain) throws IOException{
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + tokenComponent.GetAccessToken());
                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        };

        OkHttpClient finalClient = httpClient.addInterceptor(headerIntercept).addInterceptor(logging).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("https://platform.whereismytransport.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(finalClient)
                .build();
        
        return restAdapter.create(ITransportApi.class);
    }
}
