package mihir.dbscan.data;

import java.util.ArrayList;

public class TwoDimPoint {
	private double x,y;
	private static double dist=0;
	private double tmp_distance;
	private int clusterId = 0;
	private int id;
	private ArrayList<TwoDimPoint> nearestList;//contains Id of nearest points under epsilon
	int noOfNearestNeighbour;
	double rank=0;
	public TwoDimPoint(double x,double y,int id) {
		// set points value
		this.x=x;
		this.y=y;
		this.id=id;
		this.rank=0;
		}
	public TwoDimPoint(int id,double x,double y,int clusterId,ArrayList<TwoDimPoint> nearestList,int noOfNearestNeighbour,int rank)
	{
		this.x=x;
		this.y=y;
		this.id=id;
		this.rank=0;
		this.clusterId=0;
		this.nearestList = nearestList;
		this.noOfNearestNeighbour= noOfNearestNeighbour;
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
	/**
	 * @param rank the rank to set
	 */
	public void setRank(double rank) {
		this.rank = rank;
	}
	
	/**
	 * @return the rank
	 */
	public double getRank() {
		return rank;
	}
	/**
	 * @param tempClusterId the tempClusterId to set
	 */
	//calculates Euclidean distance.More Distance functions can be added
	static public double EuclideanDistance(TwoDimPoint X1,TwoDimPoint X2)
	{
		dist = Math.sqrt((X1.getX()-X2.getX())*(X1.getX()-X2.getX())+(X1.getY()-X2.getY())*(X1.getY()-X2.getY()));
		return dist;
		
	}

/**
 * @param tmp_distance the tmp_distance to set
 */
public void setTmp_distance(double tmp_distance) {
	this.tmp_distance = tmp_distance;
}
	

/**
 * @return the tmp_distance
 */
public double getTmp_distance() {
	return tmp_distance;
}

}
