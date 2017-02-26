package transportapisdk;

import java.util.List;

public class FareProductQueryOptions extends QueryOptions
{
	public FareProductQueryOptions(List<String> onlyAgencies, List<String> omitAgencies, int limit, int offset, String exclude) {
		super(onlyAgencies, omitAgencies, defaultModes, defaultModes, limit, offset, exclude);
	}
	
	public static FareProductQueryOptions defaultQueryOptions()
	{
		return new FareProductQueryOptions(defaultAgencies, defaultAgencies, defaultLimit, defaultOffset, defaultExclude);
	}
}
