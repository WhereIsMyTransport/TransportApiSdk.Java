package transportapisdk;

import java.util.ArrayList;
import java.util.List;
import transportapisdk.models.MultiPoint;
import transportapisdk.models.Omit;
import transportapisdk.models.Only;
import transportapisdk.models.Profile;
import transportapisdk.models.TimeType;

@SuppressWarnings("unused")
class JourneyInput {

	private MultiPoint geometry;
    private String time;
    private TimeType timeType;
    private Profile profile;
    private Only only;
    private Omit omit;
    private int maxItineraries;
    private List<String> fareProducts = new ArrayList<String>();

    public JourneyInput(
    		MultiPoint geometry,
    		String time,
    		TimeType timeType,
    		Profile profile,
    		List<String> onlyAgencies, 
			List<String> omitAgencies, 
			List<String> onlyModes, 
			List<String> omitModes, 
    		int maxItineraries,
    		List<String> fareProducts)
    {
        this.geometry = geometry;
        this.time = time;
        this.timeType = timeType;
        this.maxItineraries = maxItineraries;
        this.profile = profile;
        this.fareProducts = fareProducts;
        
        Only only = new Only();
        only.setAgencies(onlyAgencies);
        only.setModes(onlyModes);
        
        Omit omit = new Omit();
        omit.setAgencies(omitAgencies);
        omit.setModes(omitModes);
        
        this.only = only;
        this.omit = omit;
    }

}

