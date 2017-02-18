package transportapisdk;

import transportapisdk.models.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.converter.gson.GsonConverterFactory;

interface ITransportApi
{
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies")
	Call<List<Agency>> GetAgencies();
}

public class TransportApiClient {
	
	private TokenComponent tokenComponent;

	private final CountDownLatch latch = new CountDownLatch(1);
	
	// TODO Not the greatest, but these are all the return values for the callbacks to set.
	private List<Agency> agencies = null;
    
    public TransportApiClient(TransportApiClientSettings settings)
    {
    	if (settings == null)
    	{
    		throw new IllegalArgumentException("Settings cannot be null.");
    	}
    	
    	this.tokenComponent = new TokenComponent(settings.ClientId, settings.ClientSecret);
    }
    
    public List<Agency> GetAgencies()
    {
    	ITransportApi service = GetTransportApiClient();
        
        Call<List<Agency>> call = service.GetAgencies();
        
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
    
    private ITransportApi GetTransportApiClient()
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

   /* interface TapiApiInterface {

    	@Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("agencies")
    Call<List<Agency>>  getAgencies();

   /* 	
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("api/agencies?point={point}&radius={radius}&bbox={bbox}&agencies={agencies}&limit={limit}&offset={offset}&at={at}")
        Call<List<Agency>>  getAgencies(@Path("point") Point point, @Path("radius") int radius, @Path("bbox") List<String> bbox,@Path("agencies") List<String> agencies,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at );
*/
/*
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("agencies/{agencyId}?at={at}")
        Call<Agency>  getAgencyByID(@Path("agencyId") String id,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops?point={point}&radius={radius}&bbox={bbox}&modes={modes}&agencies={agencies}&servesLines={lineIds}&limit={limit}&offset={offset}&at={at}")
        Call<List<Stop>> getStops(@Path("point") Point point,@Path("radius") int radius,@Path("bbox") List<String> bbox,@Path("modes") List<String> modes,@Path("agencies") List<String> agencies,@Path("servesLine") String servesLine,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops/{stopId}?at={at}")
        Call<Stop> getStopByID(@Path("stopId") String stopId,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("stops/{stopId}/timetables?earliestArrivalTime={earliestArrivalTime}&limit={limit}&at={at}")
        Call<StopTimetable> getStopTimetable(@Path("stopId") String stopId,@Path("earliestArrivalTime") String earliestArrivalTime,@Path("limit") int limit,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines?agencies={agencies}&servesStops={servesStops}&limit={limit}&offset={offset}&at={at}")
        Call<List<Line>> getLines(@Path("agencies") List<String> agencies,@Path("servesStops") String servesStops,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}?at={at}")
        Call<Line> getLineByID(@Path("lineId") String lineId);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}/timetables?earliestDepartureTime={earliestDepartureTime}&limit={limit}&at={at}")
        Call<LineTimetable> getLineTimetable(@Path("lineId") String lineId,@Path("earliestDepartureTime") String earliestDepartureTime,@Path("limit") int limit,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("lines/{lineId}/shape?at={at}")
        Call<LineShape> getLineShape(@Path("lineId") String lineId,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}?fareproducts={fareProductIds}")
        Call<Journey> getJourneyByID(@Path("journeyId") String journeyId,@Path("fareproducts") String fareProducts);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}/itineraries/{itineraryId}?fareproducts={fareProductIds}")
        Call<Itinerary> getItineraryByID(@Path("journeyId") String journeyId,@Path("itineraryId") String itineraryId,@Path("fareProductIds") List<String> fareProducts);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("journeys/{journeyId}/itineraries?fareproducts={fareProductIds}")
        Call<Itinerary> getItineraryWithApplicationOfFareProduct(@Path("journeyId") String journeyId,@Path("fareProductIds") List<String> fareProductIds);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts?agencies={agencies}&limit={limit}&offset={offset}&at={at}")
        Call<List<FareProduct>> getFareProducts(@Path("agencies") List<String> agencies,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts/{fareProductId}/faretables?limit={limit}&offset={offset}&at={at}")
        Call<List<FareTable>> getFareTables(@Path("fareProductId") String fareProductId, @Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("fareproducts/{fareProductId}/faretables/{fareTableId}?limit={limit}&offset={offset}&at={at}")
        Call<FareTable> getFareTableByID(@Path("fareProductId") String fareProductId,@Path("fareTableId") String fareTableId, @Path("limit") int limit,@Path("offset") int offset,@Path("at") String at);


        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @POST("journeys")
        Call<Journey> getJourney(@Body JourneyPOST journey);
    }*/
}
