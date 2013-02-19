package mihir.dbscan.data;

import java.util.ArrayList;

public class TwoDimPoint {
	private double x,y;
	private static double dist=0;
	private int clusterId = 0;
	private int id;
	private static int count=0;
	private ArrayList<TwoDimPoint> nearestList;//contains Id of nearest points under epsilon
	int noOfNearestNeighbour;
	public TwoDimPoint(double x,double y) {
		// set points value
		this.x=x;
		this.y=y;
		this.id=++count;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setNearestList(ArrayList<TwoDimPoint> tmp) {
		this.nearestList = tmp;
		setNoOfNearestNeighbour(tmp.size());
	}
	public void setNoOfNearestNeighbour(int noOfNearestNeighbour) {
		this.noOfNearestNeighbour = noOfNearestNeighbour;
	}
   public int getNoOfNearestNeighbour() {
	   return noOfNearestNeighbour;
   }
	public ArrayList<TwoDimPoint> getNearestList() {
		return nearestList;
	}
	//calculates Euclidean distance.More Distance functions can be added
	static public double EuclideanDistance(TwoDimPoint X1,TwoDimPoint X2)
	{
		dist = Math.sqrt((X1.getX()-X2.getX())*(X1.getX()-X2.getX())+(X1.getY()-X2.getY())*(X1.getY()-X2.getY()));
		return dist;
		
	}

	
	

}
