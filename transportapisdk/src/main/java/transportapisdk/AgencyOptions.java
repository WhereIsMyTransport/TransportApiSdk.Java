package transportapisdk;

import java.util.List;

public class AgencyOptions extends Options
{
	public AgencyOptions(List<String> onlyAgencies, List<String> omitAgencies, int limit, int offset, String exclude) {
		super(onlyAgencies, omitAgencies, limit, offset, exclude);
	}
	
	public static AgencyOptions Default()
	{
		return new AgencyOptions(defaultAgencies, defaultAgencies, defaultLimit, defaultOffset, defaultExclude);
	}
}
