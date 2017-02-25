package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;

public class Point extends Geometry 
{
	private List<Double> coordinates;
	
	public Point(double longtitude, double latitude) 
	{		
		super("Point");
		
		coordinates = new ArrayList<Double>();
		
        coordinates.add(longtitude);
        coordinates.add(latitude);
	}

	public double[] getCoordinates() 
	{
        double[] coords = new double[2];
        
        coords[0] = coordinates.get(0);
        coords[1] = coordinates.get(1);
        
		return coords;
	}
	
	public List<Double> getCoordinatesList() 
	{
		return coordinates;
	}
	
	public String toString()
	{
		return coordinates.get(1) + "," + coordinates.get(0);
	}
}
