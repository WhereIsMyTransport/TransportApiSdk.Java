package transportapisdk;

public class LineTimetableQueryOptions extends QueryOptions
{
	protected final static String defaultEarliestDepartureTime = null;
	protected final static String defaultlatestDepartureTime = null;
	protected final static String defaultDepartureStopId = null;
	protected final static String defaultArrivalStopId = null;
	
	public String earliestDepartureTime;
	public String latestDepartureTime;
	public String departureStopId;
	public String arrivalStopId;
	
	public LineTimetableQueryOptions(String earliestDepartureTime, String latestDepartureTime, String departureStopId, String arrivalStopId, int limit, int offset, String exclude) 
	{
		super(defaultAgencies, defaultAgencies, defaultModes, defaultModes, limit, offset, exclude);
			
		this.earliestDepartureTime = earliestDepartureTime;
		this.latestDepartureTime = latestDepartureTime;
		this.departureStopId = departureStopId;
		this.arrivalStopId = arrivalStopId;
	}
	
	public static LineTimetableQueryOptions defaultQueryOptions()
	{
		return new LineTimetableQueryOptions(defaultEarliestDepartureTime, defaultlatestDepartureTime, defaultDepartureStopId, defaultArrivalStopId, defaultLimit, defaultOffset, defaultExclude);
	}
}
