package transportapisdk;

import java.util.List;

public class LineQueryOptions extends QueryOptions
{
	protected final static List<String> defaultServesStops = null;
	
	public List<String> servesStops;
	
	public LineQueryOptions(List<String> onlyAgencies, List<String> omitAgencies, List<String> onlyModes, List<String> omitModes, List<String> servesStops, int limit, int offset, String exclude) 
	{
		super(onlyAgencies, omitAgencies, onlyModes, omitModes, limit, offset, exclude);
			
		this.servesStops = servesStops;
	}
	
	public static LineQueryOptions defaultQueryOptions()
	{
		return new LineQueryOptions(defaultAgencies, defaultAgencies, defaultModes, defaultModes, defaultServesStops, defaultLimit, defaultOffset, defaultExclude);
	}
}

