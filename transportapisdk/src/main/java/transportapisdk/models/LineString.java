package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class LineString extends Geometry {
	
	private List<List<Double>> coordinates;
	
	public LineString(List<List<Double>> coordinates) {
		super(LineString.class.getSimpleName());
		this.coordinates = coordinates;
	}
	
	public List<List<Double>> getCoordinates() {
		return coordinates;
	}
}

