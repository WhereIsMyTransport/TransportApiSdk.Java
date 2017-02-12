package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class MultiPolygon extends Geometry {
	
	private List<Polygon> coordinates;
	
	public MultiPolygon(List<Polygon> coordinates) {
		super(MultiPolygon.class.getSimpleName());
		this.coordinates = coordinates;
	}
	
	public List<List<List<List<Double>>>>  getCoordinates() {
		List<List<List<List<Double>>>> multiPolygon = new ArrayList<List<List<List<Double>>>>();
		for (Polygon polygon: coordinates) {
			multiPolygon.add(polygon.getCoordinates());
		}
		return multiPolygon;
	}
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
