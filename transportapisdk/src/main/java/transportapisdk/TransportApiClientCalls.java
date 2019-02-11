package transportapisdk;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
	Call<Journey> PostJourney(@Header("Unique-Context-Id") String uniqueContextId, @Body JourneyInput journey, @Query("exclude") String exclude);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("journeys/{journeyId}")
	Call<Journey> GetJourney(@Header("Unique-Context-Id") String uniqueContextId, @Path("journeyId") String journeyId, @Query("exclude") String exclude);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("journeys/{journeyId}/itineraries/{itineraryId}")
	Call<Itinerary> GetItinerary(@Header("Unique-Context-Id") String uniqueContextId, @Path("journeyId") String journeyId, @Path("itineraryId") String itineraryId, @Query("exclude") String exclude);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies")
	Call<List<Agency>> GetAgencies(@Header("Unique-Context-Id") String uniqueContextId, @Query("agencies") List<String> agencies, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("agencies/{agencyId}")
	Call<Agency> GetAgency(@Header("Unique-Context-Id") String uniqueContextId, @Path("agencyId") String id);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("stops")
	Call<List<Stop>> GetStops(@Header("Unique-Context-Id") String uniqueContextId, @Query("agencies") List<String> agencies, @Query("modes") List<String> modes, @Query("servesLines") List<String> servesLines, @Query("showChildren") boolean showChildren, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);


	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("stops/{stopId}")
	Call<Stop> GetStop(@Header("Unique-Context-Id") String uniqueContextId, @Path("stopId") String stopId);

	@Headers({
		"Accept: application/json",
		"Content-Type: application/json"
	})
	@GET("lines")
	Call<List<Line>> GetLines(@Header("Unique-Context-Id") String uniqueContextId, @Query("agencies") List<String> agencies, @Query("modes") List<String> modes, @Query("servesStops") List<String> servesStops, @Query("limit") int limit, @Query("offset") int offset, @Query("point") Point point, @Query("radius") Integer radius, @Query("bbox") String bbox, @Query("exclude") String exclude);

	@Headers({
	    "Accept: application/json",
	    "Content-Type: application/json"
	})
	@GET("lines/{lineId}")
	Call<Line> GetLine(@Header("Unique-Context-Id") String uniqueContextId, @Path("lineId") String lineId);

	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("fareproducts")
	Call<List<FareProduct>> GetFareProducts(@Header("Unique-Context-Id") String uniqueContextId, @Query("agencies") List<String> agencies, @Query("limit") int limit, @Query("offset") int offset, @Query("exclude") String exclude);

	@Headers({
	    "Accept: application/json",
	    "Content-Type: application/json"
	})
	@GET("fareproducts/{fareProductId}")
	Call<FareProduct> GetFareProduct(@Header("Unique-Context-Id") String uniqueContextId, @Path("fareProductId") String fareProductId);
	
	@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
	})
	@GET("stops/{stopId}/timetables")
	Call<List<StopTimetable>> GetStopTimetable(@Header("Unique-Context-Id") String uniqueContextId, @Path("stopId") String stopId, @Query("earliestArrivalTime") String earliestArrivalTime, @Query("latestArrivalTime") String latestArrivalTime, @Query("limit") int limit, @Query("offset") int offset, @Query("exclude") String exclude);

	@Headers({
	        "Accept: application/json",
	        "Content-Type: application/json"
	})
	@GET("lines/{lineId}/timetables")
	Call<List<LineTimetable>> GetLineTimetable(@Header("Unique-Context-Id") String uniqueContextId, @Path("lineId") String lineId, @Query("earliestDepartureTime") String earliestDepartureTime, @Query("latestDepartureTime") String latestDepartureTime, @Query("departureStopId") String departureStopId, @Query("arrivalStopId") String arrivalStopId, @Query("limit") int limit, @Query("offset") int offset, @Query("exclude") String exclude);

}

class TransportApiClientCalls 
{
	private static TransportApiResult<List<Agency>> agencies = new TransportApiResult<List<Agency>>();
	private static TransportApiResult<Agency> agency = new TransportApiResult<Agency>();
	private static TransportApiResult<Journey> journey = new TransportApiResult<Journey>();
	private static TransportApiResult<Itinerary> itinerary = new TransportApiResult<Itinerary>();
	private static TransportApiResult<List<Line>> lines = new TransportApiResult<List<Line>>();
	private static TransportApiResult<Line> line = new TransportApiResult<Line>();
	private static TransportApiResult<List<Stop>> stops = new TransportApiResult<List<Stop>>();
	private static TransportApiResult<Stop> stop = new TransportApiResult<Stop>();
	private static TransportApiResult<List<FareProduct>> fareProducts = new TransportApiResult<List<FareProduct>>();
	private static TransportApiResult<FareProduct> fareProduct = new TransportApiResult<FareProduct>();
	private static TransportApiResult<List<StopTimetable>> stopTimetable = new TransportApiResult<List<StopTimetable>>();
	private static TransportApiResult<List<LineTimetable>> lineTimetable = new TransportApiResult<List<LineTimetable>>();
	
	public static TransportApiResult<Journey> postJourney(final TokenComponent tokenComponent, final TransportApiClientSettings settings, JourneyBodyOptions options, Point start, Point end, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			journey.isSuccess = accessToken.isSuccess;
        	journey.httpStatusCode = accessToken.httpStatusCode;
        	journey.error = accessToken.error;
        	journey.data = null;
        	
        	return journey;
		}   		

		ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
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
		
        Call<Journey> call = service.PostJourney(settings.uniqueContextId, inputModel, exclude);
        
        call.enqueue(new Callback<Journey>() {
            public void onResponse(Call<Journey> call, Response<Journey> response) {
            	journey.isSuccess = response.isSuccessful();
            	journey.httpStatusCode = response.code();
            	journey.error = getErrorMessage(response.errorBody());
            	journey.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Journey> call, Throwable t) {
            	journey.isSuccess = false;
            	journey.httpStatusCode = 400;
            	journey.data = null;
            	journey.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	journey.isSuccess = false;
        	journey.httpStatusCode = 400;
        	journey.data = null;
        	journey.error = "Thread failure, try executing one call at a time.";
        }

        return journey;
    }
	
	public static TransportApiResult<Journey> getJourney(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String journeyId, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);
		
		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			journey.isSuccess = accessToken.isSuccess;
        	journey.httpStatusCode = accessToken.httpStatusCode;
        	journey.error = accessToken.error;
        	journey.data = null;
        	
        	return journey;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<Journey> call = service.GetJourney(settings.uniqueContextId, journeyId, exclude);
        
        call.enqueue(new Callback<Journey>() {
        	public void onResponse(Call<Journey> call, Response<Journey> response) {
            	journey.isSuccess = response.isSuccessful();
            	journey.httpStatusCode = response.code();
            	journey.error = getErrorMessage(response.errorBody());
            	journey.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Journey> call, Throwable t) {
            	journey.isSuccess = false;
            	journey.httpStatusCode = 400;
            	journey.data = null;
            	journey.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	journey.isSuccess = false;
        	journey.httpStatusCode = 400;
        	journey.data = null;
        	journey.error = "Thread failure, try executing one call at a time.";
        }

        return journey;
    }
	
	public static TransportApiResult<Itinerary> getItinerary(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String journeyId, String itineraryId, String exclude)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			itinerary.isSuccess = accessToken.isSuccess;
			itinerary.httpStatusCode = accessToken.httpStatusCode;
        	itinerary.error = accessToken.error;
        	itinerary.data = null;
        	
        	return itinerary;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<Itinerary> call = service.GetItinerary(settings.uniqueContextId, journeyId, itineraryId, exclude);
        
        call.enqueue(new Callback<Itinerary>() {
        	public void onResponse(Call<Itinerary> call, Response<Itinerary> response) {
            	itinerary.isSuccess = response.isSuccessful();
            	itinerary.httpStatusCode = response.code();
            	itinerary.error = getErrorMessage(response.errorBody());
            	itinerary.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Itinerary> call, Throwable t) {
            	itinerary.isSuccess = false;
            	itinerary.httpStatusCode = 400;
            	itinerary.data = null;
            	itinerary.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	itinerary.isSuccess = false;
        	itinerary.httpStatusCode = 400;
        	itinerary.data = null;
        	itinerary.error = "Thread failure, try executing one call at a time.";
        }

        return itinerary;
    }
	
	public static TransportApiResult<List<Agency>> getAgencies(final TokenComponent tokenComponent, final TransportApiClientSettings settings, AgencyQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			agencies.isSuccess = accessToken.isSuccess;
			agencies.httpStatusCode = accessToken.httpStatusCode;
        	agencies.error = accessToken.error;
        	agencies.data = null;
        	
        	return agencies;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<Agency>> call = service.GetAgencies(settings.uniqueContextId, options.agencies, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
        call.enqueue(new Callback<List<Agency>>() {
        	public void onResponse(Call<List<Agency>> call, Response<List<Agency>> response) {
            	agencies.isSuccess = response.isSuccessful();
            	agencies.httpStatusCode = response.code();
            	agencies.error = getErrorMessage(response.errorBody());
            	agencies.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<List<Agency>> call, Throwable t) {
            	agencies.isSuccess = false;
            	agencies.httpStatusCode = 400;
            	agencies.data = null;
            	agencies.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	agencies.isSuccess = false;
        	agencies.httpStatusCode = 400;
        	agencies.data = null;
        	agencies.error = "Thread failure, try executing one call at a time.";
        }

        return agencies;
    }
    
	public static TransportApiResult<Agency> getAgency(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String agencyId)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			agency.isSuccess = accessToken.isSuccess;
			agency.httpStatusCode = accessToken.httpStatusCode;
        	agency.error = accessToken.error;
        	agency.data = null;
        	
        	return agency;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<Agency> call = service.GetAgency(settings.uniqueContextId, agencyId);
        
        call.enqueue(new Callback<Agency>() {
        	public void onResponse(Call<Agency> call, Response<Agency> response) {
            	agency.isSuccess = response.isSuccessful();
            	agency.httpStatusCode = response.code();
            	agency.error = getErrorMessage(response.errorBody());
            	agency.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Agency> call, Throwable t) {
            	agency.isSuccess = false;
            	agency.httpStatusCode = 400;
            	agency.data = null;
            	agency.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	agency.isSuccess = false;
        	agency.httpStatusCode = 400;
        	agency.data = null;
        	agency.error = "Thread failure, try executing one call at a time.";
        }

        return agency;
    }
	
	public static TransportApiResult<List<Line>> getLines(final TokenComponent tokenComponent, final TransportApiClientSettings settings, LineQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			lines.isSuccess = accessToken.isSuccess;
			lines.httpStatusCode = accessToken.httpStatusCode;
        	lines.error = accessToken.error;
        	lines.data = null;
        	
        	return lines;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<Line>> call = service.GetLines(settings.uniqueContextId, options.agencies, options.modes, options.servesStops, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
        call.enqueue(new Callback<List<Line>>() {
        	public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
            	lines.isSuccess = response.isSuccessful();
            	lines.httpStatusCode = response.code();
            	lines.error = getErrorMessage(response.errorBody());
            	lines.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<List<Line>> call, Throwable t) {
            	lines.isSuccess = false;
            	lines.httpStatusCode = 400;
            	lines.data = null;
            	lines.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	lines.isSuccess = false;
        	lines.httpStatusCode = 400;
        	lines.data = null;
        	lines.error = "Thread failure, try executing one call at a time.";
        }

        return lines;
    }
    
	public static TransportApiResult<Line> getLine(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String lineId)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			line.isSuccess = accessToken.isSuccess;
			line.httpStatusCode = accessToken.httpStatusCode;
        	line.error = accessToken.error;
        	line.data = null;
        	
        	return line;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<Line> call = service.GetLine(settings.uniqueContextId, lineId);
        
        call.enqueue(new Callback<Line>() {
        	public void onResponse(Call<Line> call, Response<Line> response) {
            	line.isSuccess = response.isSuccessful();
            	line.httpStatusCode = response.code();
            	line.error = getErrorMessage(response.errorBody());
            	line.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Line> call, Throwable t) {
            	line.isSuccess = false;
            	line.httpStatusCode = 400;
            	line.data = null;
            	line.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	line.isSuccess = false;
        	line.httpStatusCode = 400;
        	line.data = null;
        	line.error = "Thread failure, try executing one call at a time.";
        }

        return line;
    }
	
	public static TransportApiResult<List<LineTimetable>> getLineTimetable(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String lineId, LineTimetableQueryOptions options)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			lineTimetable.isSuccess = accessToken.isSuccess;
			lineTimetable.httpStatusCode = accessToken.httpStatusCode;
        	lineTimetable.error = accessToken.error;
        	lineTimetable.data = null;
        	
        	return lineTimetable;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<LineTimetable>> call = service.GetLineTimetable(settings.uniqueContextId, lineId, options.earliestDepartureTime, options.latestDepartureTime, options.departureStopId, options.arrivalStopId, options.limit, options.offset, options.exclude);
                
        call.enqueue(new Callback<List<LineTimetable>>() {
            public void onResponse(Call<List<LineTimetable>> call, Response<List<LineTimetable>> response) {
            	lineTimetable.isSuccess = response.isSuccessful();
            	lineTimetable.httpStatusCode = response.code();
            	lineTimetable.error = getErrorMessage(response.errorBody());
            	lineTimetable.data = response.body();

                latch.countDown();
            }

            public void onFailure(Call<List<LineTimetable>> call, Throwable t) {
            	lineTimetable.isSuccess = false;
            	lineTimetable.httpStatusCode = 400;
            	lineTimetable.data = null;
            	lineTimetable.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	lineTimetable.isSuccess = false;
        	lineTimetable.httpStatusCode = 400;
        	lineTimetable.data = null;
        	lineTimetable.error = "Thread failure, try executing one call at a time.";
        }

        return lineTimetable;
    }
	
	public static TransportApiResult<List<Stop>> getStops(final TokenComponent tokenComponent, final TransportApiClientSettings settings, StopQueryOptions options, Point point, Integer radiusInMeters, String boundingBox)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			stops.isSuccess = accessToken.isSuccess;
			stops.httpStatusCode = accessToken.httpStatusCode;
        	stops.error = accessToken.error;
        	stops.data = null;
        	
        	return stops;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<Stop>> call = service.GetStops(settings.uniqueContextId, options.agencies, options.modes, options.servesLines, options.showChildren, options.limit, options.offset, point, radiusInMeters, boundingBox, options.exclude);
        
        call.enqueue(new Callback<List<Stop>>() {
        	public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
            	stops.isSuccess = response.isSuccessful();
            	stops.httpStatusCode = response.code();
            	stops.error = getErrorMessage(response.errorBody());
            	stops.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<List<Stop>> call, Throwable t) {
            	stops.isSuccess = false;
            	stops.httpStatusCode = 400;
            	stops.data = null;
            	stops.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	stops.isSuccess = false;
        	stops.httpStatusCode = 400;
        	stops.data = null;
        	stops.error = "Thread failure, try executing one call at a time.";
        }

        return stops;
    }
    
	public static TransportApiResult<Stop> getStop(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String stopId)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			stop.isSuccess = accessToken.isSuccess;
			stop.httpStatusCode = accessToken.httpStatusCode;
        	stop.error = accessToken.error;
        	stop.data = null;
        	
        	return stop;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<Stop> call = service.GetStop(settings.uniqueContextId, stopId);
        
        call.enqueue(new Callback<Stop>() {
        	public void onResponse(Call<Stop> call, Response<Stop> response) {
            	stop.isSuccess = response.isSuccessful();
            	stop.httpStatusCode = response.code();
            	stop.error = getErrorMessage(response.errorBody());
            	stop.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<Stop> call, Throwable t) {
            	stop.isSuccess = false;
            	stop.httpStatusCode = 400;
            	stop.data = null;
            	stop.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	stop.isSuccess = false;
        	stop.httpStatusCode = 400;
        	stop.data = null;
        	stop.error = "Thread failure, try executing one call at a time.";
        }

        return stop;
    }
	
	public static TransportApiResult<List<StopTimetable>> getStopTimetable(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String stopId, StopTimetableQueryOptions options)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			stopTimetable.isSuccess = accessToken.isSuccess;
			stopTimetable.httpStatusCode = accessToken.httpStatusCode;
        	stopTimetable.error = accessToken.error;
        	stopTimetable.data = null;
        	
        	return stopTimetable;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<StopTimetable>> call = service.GetStopTimetable(settings.uniqueContextId, stopId, options.earliestArrivalTime, options.latestArrivalTime, options.limit, options.offset, options.exclude);
        
        call.enqueue(new Callback<List<StopTimetable>>() {
        	public void onResponse(Call<List<StopTimetable>> call, Response<List<StopTimetable>> response) {
            	stopTimetable.isSuccess = response.isSuccessful();
            	stopTimetable.httpStatusCode = response.code();
            	stopTimetable.error = getErrorMessage(response.errorBody());
            	stopTimetable.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<List<StopTimetable>> call, Throwable t) {
            	stopTimetable.isSuccess = false;
            	stopTimetable.httpStatusCode = 400;
            	stopTimetable.data = null;
            	stopTimetable.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	stopTimetable.isSuccess = false;
        	stopTimetable.httpStatusCode = 400;
        	stopTimetable.data = null;
        	stopTimetable.error = "Thread failure, try executing one call at a time.";
        }

        return stopTimetable;
    }
    
	public static TransportApiResult<List<FareProduct>> getFareProducts(final TokenComponent tokenComponent, final TransportApiClientSettings settings, FareProductQueryOptions options)
    {
		final CountDownLatch latch = new CountDownLatch(1);

		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			fareProducts.isSuccess = accessToken.isSuccess;
			fareProducts.httpStatusCode = accessToken.httpStatusCode;
        	fareProducts.error = accessToken.error;
        	fareProducts.data = null;
        	
        	return fareProducts;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<List<FareProduct>> call = service.GetFareProducts(settings.uniqueContextId, options.agencies, options.limit, options.offset, options.exclude);
        
        call.enqueue(new Callback<List<FareProduct>>() {
        	public void onResponse(Call<List<FareProduct>> call, Response<List<FareProduct>> response) {
            	fareProducts.isSuccess = response.isSuccessful();
            	fareProducts.httpStatusCode = response.code();
            	fareProducts.error = getErrorMessage(response.errorBody());
            	fareProducts.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<List<FareProduct>> call, Throwable t) {
            	fareProducts.isSuccess = false;
            	fareProducts.httpStatusCode = 400;
            	fareProducts.data = null;
            	fareProducts.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	fareProducts.isSuccess = false;
        	fareProducts.httpStatusCode = 400;
        	fareProducts.data = null;
        	fareProducts.error = "Thread failure, try executing one call at a time.";
        }

        return fareProducts;
    }
    
	public static TransportApiResult<FareProduct> getFareProduct(final TokenComponent tokenComponent, final TransportApiClientSettings settings, String fareProductId)
	{
		final CountDownLatch latch = new CountDownLatch(1);
		
		TransportApiResult<String> accessToken = tokenComponent.getAccessToken();
		
		if (!accessToken.isSuccess)
		{
			fareProduct.isSuccess = accessToken.isSuccess;
			fareProduct.httpStatusCode = accessToken.httpStatusCode;
        	fareProduct.error = accessToken.error;
        	fareProduct.data = null;
        	
        	return fareProduct;
		}
		
    	ITransportApi service = getTransportApiClient(tokenComponent, settings, accessToken.data);
        
        Call<FareProduct> call = service.GetFareProduct(settings.uniqueContextId, fareProductId);
        
        call.enqueue(new Callback<FareProduct>() {
        	public void onResponse(Call<FareProduct> call, Response<FareProduct> response) {
            	fareProduct.isSuccess = response.isSuccessful();
            	fareProduct.httpStatusCode = response.code();
            	fareProduct.error = getErrorMessage(response.errorBody());
            	fareProduct.data = response.body();
            	
                latch.countDown();
            }

            public void onFailure(Call<FareProduct> call, Throwable t) {
            	fareProduct.isSuccess = false;
            	fareProduct.httpStatusCode = 400;
            	fareProduct.data = null;
            	fareProduct.error = t.getMessage();
                
                latch.countDown();
            }
        });
        
        try
        {
           latch.await();
        }
        catch (InterruptedException e)
        {
        	fareProduct.isSuccess = false;
        	fareProduct.httpStatusCode = 400;
        	fareProduct.data = null;
        	fareProduct.error = "Thread failure, try executing one call at a time.";
        }

        return fareProduct;
    }
	
    private static ITransportApi getTransportApiClient(final TokenComponent tokenComponent, final TransportApiClientSettings settings, final String accessToken)
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Interceptor headerIntercept = new Interceptor()
        {
            public okhttp3.Response intercept(Chain chain) throws IOException{
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + accessToken);
                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        };

        OkHttpClient finalClient = httpClient.addInterceptor(headerIntercept).addInterceptor(logging).readTimeout(settings.timeoutInSeconds, TimeUnit.SECONDS).connectTimeout(settings.timeoutInSeconds, TimeUnit.SECONDS).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(settings.environmentUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(finalClient)
                .build();
        
        return restAdapter.create(ITransportApi.class);
    }

    private static String getErrorMessage(ResponseBody errorBody)
    {
    	if (errorBody != null)
    	{
			try 
			{
				JSONObject jObject = new JSONObject(errorBody.string());

				return jObject.toString();
			} 
			catch (JSONException e) 
			{
				return "An unknown error occurred.";
			} 
			catch (IOException e) 
			{
				return "An unknown error occurred.";
			}
    	}
    	else
    		return "";
    }
}