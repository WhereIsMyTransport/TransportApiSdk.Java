package transportapisdk;

public class StopTimetableQueryOptions extends QueryOptions
{
	protected final static String defaultEarliestArrivalTime = null;
	protected final static String defaultlatestArrivalTime = null;
	
	public String earliestArrivalTime;
	public String latestArrivalTime;
	
	public StopTimetableQueryOptions(String earliestArrivalTime, String latestArrivalTime, int limit, int offset, String exclude) 
	{
		super(defaultAgencies, defaultAgencies, defaultModes, defaultModes, limit, offset, exclude);
			
		this.earliestArrivalTime = earliestArrivalTime;
		this.latestArrivalTime = latestArrivalTime;
	}
	
	public static StopTimetableQueryOptions defaultQueryOptions()
	{
		return new StopTimetableQueryOptions(defaultEarliestArrivalTime, defaultlatestArrivalTime, defaultLimit, defaultOffset, defaultExclude);
	}
}
