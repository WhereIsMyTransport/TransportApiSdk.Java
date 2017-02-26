package transportapisdk;

import java.io.IOException;
import java.util.Arrays;
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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import transportapisdk.models.*;

interface ITransportApi
{
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@POST("journeys")
	Call<Journey> PostJourney(@Body JourneyInput journey, @Query("exclude") String exclude);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("journeys/{journeyId}")
	Call<Journey> GetJourney(@Path("journeyId") String journeyId, @Query("exclude") String exclude);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("journeys/{journeyId}/itineraries/{itineraryId}")
	Call<Itinerary> GetItinerary(@Path("journeyId") String journeyId, @Path("itineraryId") String itineraryId, @Query("exclude") String exclude);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies")
	Call<List<Agency>> GetAgencies(@Query("agencies") List<String> agencies, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies/{agencyId}")
	Call<Agency> GetAgency(@Path("agencyId") String id);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("stops")
	Call<List<Stop>> GetStops(@Query("agencies") List<String> agencies, @Query("modes") List<String> modes, @Query("servesLines") List<String> servesLines, @Query("showChildren") boolean showChildren, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);


	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("stops/{stopId}")
	Call<Stop> GetStop(@Path("stopId") String stopId);

	@Headers({
		"Accept: application/json",
		"Content-Type: application/json"
	})
	@GET("lines")
	Call<List<Line>> GetLines(@Query("agencies") List<String> agencies, @Query("modes") List<String> modes, @Query("servesStops") List<String> servesStops, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);

	@Headers({
	    "Accept: application/json",
	    "Content-Type: application/json"
	})
	@GET("lines/{lineId}")
	Call<Line> GetLine(@Path("lineId") String lineId);

}

class TransportApiClientCalls 
{
	//private final static CountDownLatch latch = new CountDownLatch(1);
	
	// TODO Not the greatest, but these are all the return values for the call-backs to set.
	private static List<Agency> agencies = null;
	private static Agency agency = null;
	private static Journey journey = null;
	private static Itinerary itinerary = null;
	private static List<Line> lines = null;
	private static Line line = null;
	private static List<Stop> stops = null;
	private static Stop stop = null;
    
	public static Journey PostJourney(final TokenComponent tokenComponent, JourneyBodyOptions options, Point start, Point end, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
		ITransportApi service = GetTransportApiClient(tokenComponent);
        
		@SuppressWarnings("unchecked")
		MultiPoint geometry = new MultiPoint(Arrays.asList(start.getCoordinatesList(), end.getCoordinatesList()));
		
		JourneyInput inputModel = new JourneyInput(
				geometry,
	    		options.time,
	    		options.timeType,
	    		options.profile,
	    		options.onlyAgencies,
	    		options.omitAgencies,
	    		options.onlyModes,
	    		options.omitModes,
	    		options.maxItineraries,
	    		options.fareProducts);
		
        Call<Journey> call = service.PostJourney(inputModel, exclude);
        
        call.enqueue(new Callback<Journey>() {
            public void onResponse(Call<Journey> call, Response<Journey> response) {
            	journey = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Journey> call, Throwable t) {
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

        return journey;
    }
	
	public static Journey GetJourney(final TokenComponent tokenComponent, String journeyId, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<Journey> call = service.GetJourney(journeyId, exclude);
        
        call.enqueue(new Callback<Journey>() {
            public void onResponse(Call<Journey> call, Response<Journey> response) {
            	journey = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Journey> call, Throwable t) {
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

        return journey;
    }
	
	public static Itinerary GetItinerary(final TokenComponent tokenComponent, String journeyId, String itineraryId, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<Itinerary> call = service.GetItinerary(journeyId, itineraryId, exclude);
        
        call.enqueue(new Callback<Itinerary>() {
            public void onResponse(Call<Itinerary> call, Response<Itinerary> response) {
            	itinerary = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Itinerary> call, Throwable t) {
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

        return itinerary;
    }
	
	public static List<Agency> GetAgencies(final TokenComponent tokenComponent, AgencyQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<List<Agency>> call = service.GetAgencies(options.agencies, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
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
    
	public static Agency GetAgency(final TokenComponent tokenComponent, String agencyId)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<Agency> call = service.GetAgency(agencyId);
        
        call.enqueue(new Callback<Agency>() {
            public void onResponse(Call<Agency> call, Response<Agency> response) {
            	agency = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Agency> call, Throwable t) {
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

        return agency;
    }
	
	public static List<Line> GetLines(final TokenComponent tokenComponent, LineQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<List<Line>> call = service.GetLines(options.agencies, options.modes, options.servesStops, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
        call.enqueue(new Callback<List<Line>>() {
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
            	lines = response.body();

                latch.countDown();
            }

            public void onFailure(Call<List<Line>> call, Throwable t) {
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

        return lines;
    }
    
	public static Line GetLine(final TokenComponent tokenComponent, String lineId)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<Line> call = service.GetLine(lineId);
        
        call.enqueue(new Callback<Line>() {
            public void onResponse(Call<Line> call, Response<Line> response) {
            	line = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Line> call, Throwable t) {
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

        return line;
    }
	
	public static List<Stop> GetStops(final TokenComponent tokenComponent, StopQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<List<Stop>> call = service.GetStops(options.agencies, options.modes, options.servesLines, options.showChildren, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
        call.enqueue(new Callback<List<Stop>>() {
            public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
            	stops = response.body();

                latch.countDown();
            }

            public void onFailure(Call<List<Stop>> call, Throwable t) {
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

        return stops;
    }
    
	public static Stop GetStop(final TokenComponent tokenComponent, String stopId)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
    	ITransportApi service = GetTransportApiClient(tokenComponent);
        
        Call<Stop> call = service.GetStop(stopId);
        
        call.enqueue(new Callback<Stop>() {
            public void onResponse(Call<Stop> call, Response<Stop> response) {
            	stop = response.body();

                latch.countDown();
            }

            public void onFailure(Call<Stop> call, Throwable t) {
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

        return stop;
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

/* interface TapiApiInterface {


/*


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



}*/
