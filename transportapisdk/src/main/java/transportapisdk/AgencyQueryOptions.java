package transportapisdk;

import java.util.List;

public class AgencyQueryOptions extends QueryOptions
{
	public AgencyQueryOptions(List<String> onlyAgencies, List<String> omitAgencies, int limit, int offset, String exclude) {
		super(onlyAgencies, omitAgencies, defaultModes, defaultModes, limit, offset, exclude);
	}
	
	public static AgencyQueryOptions Default()
	{
		return new AgencyQueryOptions(defaultAgencies, defaultAgencies, defaultLimit, defaultOffset, defaultExclude);
	}
}
