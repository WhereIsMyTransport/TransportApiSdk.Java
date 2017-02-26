package transportapisdk;

import java.util.ArrayList;
import java.util.List;

abstract class QueryOptions 
{
	protected final static int defaultLimit = 100;
	protected final static int defaultOffset = 0;
	protected final static List<String> defaultAgencies = null;
	protected final static String defaultExclude = null;
	protected final static List<String> defaultModes = null;
	
	public List<String> agencies = null;
	public List<String> modes = null;
	public Integer limit;
	public Integer offset;
	public String exclude;
	
	public QueryOptions (List<String> onlyAgencies, List<String> omitAgencies, List<String> onlyModes, List<String> omitModes, int limit, int offset, String exclude)
	{
		if (onlyAgencies != null & omitAgencies != null)
		{
			throw new IllegalArgumentException("Either onlyAgencies or omitAgencies can be provided. Not both.");
		}
		
		if (limit < 0 || limit > 100)
		{
			throw new IllegalArgumentException("Invalid limit. Valid values are positive numbers up to 100.");
		}
		
		if (offset < 0)
		{
			throw new IllegalArgumentException("Invalid offset. Valid values are positive numbers only.");
		}
		
		if (onlyAgencies != null)
			this.agencies = onlyAgencies;
		if (omitAgencies != null)
		{
			List <String> agencies = new ArrayList<String>();
			for (String agency : omitAgencies) 
			{
				agencies.add("~" + agency);
			} 
			
			this.agencies = agencies;
		}
		
		if (onlyModes != null)
			this.modes = onlyModes;
		if (omitModes != null)
		{
			List <String> modes = new ArrayList<String>();
			for (String mode : omitModes) 
			{
				modes.add("~" + mode);
			} 
			
			this.modes = modes;
		}
		
		this.limit = limit;
		this.offset = offset;
		this.exclude = exclude;
	}
}
