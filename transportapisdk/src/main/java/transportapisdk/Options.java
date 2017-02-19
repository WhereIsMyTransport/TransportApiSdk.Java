package transportapisdk;

import java.util.List;

abstract class Options 
{
	protected final static int defaultLimit = 100;
	protected final static int defaultOffset = 0;
	protected final static List<String> defaultAgencies = null;
	protected final static String defaultExclude = null;
	
	List<String> agencies;
	Integer limit;
	Integer offset;
	String exclude;
	
	public Options (List<String> onlyAgencies, List<String> omitAgencies, int limit, int offset, String exclude)
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
		
		this.agencies = onlyAgencies;
		this.agencies = omitAgencies;
		this.limit = limit;
		this.offset = offset;
		this.exclude = exclude;
	}
}
