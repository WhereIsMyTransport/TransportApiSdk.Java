package transportapisdk;

import transportapisdk.models.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

public class TransportApiClient {

	private static AuthEndpoint authEndpoint;
    private static final String grant_type = "client_credentials";
    private static final String client_id = "f18f08cc-47e2-43aa-9442-be6098340cd1";
    private static final String scope = "transitapi:all";
    private static final String client_secret = "a0Ta5D+DG1m9znTpCgsIezWSJ0S0sx2ONU4Cw3vkCCU=";
    private static String type = "Bearer";
    private String currentToken;
    private String currentTokenExpireDate;
    private String currentTokenType;


    public TapiApiInterface getTapiApiClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Interceptor headerIntercept = new Interceptor(){
            public okhttp3.Response intercept(Chain chain) throws IOException{
                Request original =chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization",type +" " +currentToken);
                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        };

        OkHttpClient finalClient = httpClient.addInterceptor(headerIntercept).addInterceptor(logging).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("https://transit.whereismytransport.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(finalClient)
                .build();
        return restAdapter.create(TapiApiInterface.class);
    }

    //LibraryEntryPoint
    public void getAuthenticationToken(TokenListener listener){
        checkTokenRemainsValid(listener);
    }

    private void checkTokenRemainsValid(TokenListener listener){
        DateAndTimeHandler dtHandler = new DateAndTimeHandler();
        if(currentTokenExpireDate!= null & dtHandler.queryTimeDifference(currentTokenExpireDate)){
            getNewToken(listener);
        }
        else{
            listener.tokenValid(currentToken,currentTokenType,currentTokenExpireDate);
        }
    }

    private static AuthEndpoint getAuthEndpoint(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        if(authEndpoint == null){
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("https://identity.whereismytransport.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            authEndpoint =restAdapter.create(AuthEndpoint.class);
        }
        return authEndpoint;
    }


    interface AuthEndpoint{

        @FormUrlEncoded
        @POST("connect/token")
        Call<User> getUserToken(@Field("client_id") String clientID, @Field("client_secret") String clientSecret,@Field("grant_type") String grantType,@Field("scope") String scope);

    }

    interface TapiApiInterface {

        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @GET("api/agencies?point={point}&radius={radius}&bbox={bbox}&agencies={agencies}&limit={limit}&offset={offset}&at={at}")
        Call<List<Agency>>  getAgencies(@Path("point") Point point, @Path("radius") int radius, @Path("bbox") List<String> bbox,@Path("agencies") List<String> agencies,@Path("limit") int limit,@Path("offset") int offset,@Path("at") String at );


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
    }

    private void getNewToken(final TokenListener listener){
        final DateAndTimeHandler dtHandler = new DateAndTimeHandler();
        TransportApiClient.AuthEndpoint service = TransportApiClient.getAuthEndpoint();
        Call<User> call = service.getUserToken(TransportApiClient.client_id, TransportApiClient.client_secret, TransportApiClient.grant_type, TransportApiClient.scope);
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                currentToken = user.getAccessToken();
                currentTokenType = user.getTokenType();
                currentTokenExpireDate = dtHandler.getTokenExpiryDate(user.getExpiresIn());
                listener.tokenValid(currentToken,currentTokenType,currentTokenExpireDate);
            }

            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
