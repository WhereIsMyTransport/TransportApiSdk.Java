package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Geometry {
	
	private List<LineString> coordinates;
	
	public Polygon(List<LineString> coordinates) {
		super(Polygon.class.getSimpleName());
		this.coordinates = coordinates;
	}
	
	// Should throw is not a polygon exception
	public List<List<List<Double>>> getCoordinates() {
		List<List<List<Double>>> polygon = new ArrayList<List<List<Double>>>();
		for (LineString lineString : coordinates) {
			polygon.add(lineString.getCoordinates());
		}
		return polygon;
	}
}