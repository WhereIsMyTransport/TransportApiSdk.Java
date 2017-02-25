package transportapisdk.models;

import java.util.List;

public class MultiPoint extends Geometry {
	
	private List<List<Double>> coordinates;
	
	public MultiPoint(List<List<Double>> coordinates) {
		super("MultiPoint");
		
		this.coordinates = coordinates;
	}
	
	public List<List<Double>> getCoordinates() {
		return coordinates;
	}
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
