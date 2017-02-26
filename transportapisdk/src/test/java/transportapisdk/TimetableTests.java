package transportapisdk;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import transportapisdk.models.LineTimetable;
import transportapisdk.models.StopTimetable;

public class TimetableTests {
private static TransportApiClient client = new TransportApiClient(new TransportApiClientSettings(ClientCredentials.clientId, ClientCredentials.clientSecret));
	
	private static String defaultLineId = "giwBPOBfeE-C4acZAI_7uQ";
	private static String defaultStopId = "S1twiBqUm0ul6ZMtCnfOcg";

	@Test
	public void LineTimetable_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<LineTimetable>> lineTimetables = client.getLineTimetable(defaultLineId, LineTimetableQueryOptions.defaultQueryOptions());
		
		assertTrue(lineTimetables.isSuccess);
		assertTrue(lineTimetables.data.size() > 0);
	}
	
	@Test
	public void StopTimetable_DefaultValues_IsSuccess() {
		
		TransportApiResult<List<StopTimetable>> stopTimetables = client.getStopTimetable(defaultStopId, StopTimetableQueryOptions.defaultQueryOptions());
		
		assertTrue(stopTimetables.isSuccess);
		assertTrue(stopTimetables.data.size() > 0);
	}
}
