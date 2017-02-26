package transportapisdk;

import java.util.List;

public class StopQueryOptions extends QueryOptions
{
	protected final static boolean defaultShowChildren = false;
	protected final static List<String> defaultServesLines = null;
	
	public boolean showChildren;
	public List<String> servesLines;
	
	public StopQueryOptions(List<String> onlyAgencies, List<String> omitAgencies, List<String> onlyModes, List<String> omitModes, List<String> servesLines, boolean showChildren, int limit, int offset, String exclude) 
	{
		super(onlyAgencies, omitAgencies, onlyModes, omitModes, limit, offset, exclude);

		this.showChildren = showChildren;
		this.servesLines = servesLines;
	}
	
	public static StopQueryOptions Default()
	{
		return new StopQueryOptions(defaultAgencies, defaultAgencies, defaultModes, defaultModes, defaultServesLines, defaultShowChildren, defaultLimit, defaultOffset, defaultExclude);
	}
}
