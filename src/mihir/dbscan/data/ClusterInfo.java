/**
 * 
 */
package mihir.dbscan.data;

import java.util.ArrayList;

/**
 * @author mihir
 *
 */
public class ClusterInfo {

	int Id=0;
	ArrayList<Integer>nearList;
	float rank=0;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @param nearList the nearList to set
	 */
	public void setNearList(ArrayList<Integer> nearList) {
		this.nearList = nearList;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @return the nearList
	 */
	public ArrayList<Integer> getNearList() {
		return nearList;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(float rank) {
		this.rank = rank;
	}
	/**
	 * @return the rank
	 */
	public float getRank() {
		return rank;
	}


	static public double EuclideanDistance(int a,int b,ArrayList<TwoDimPoint>pnts)
	{
	    double X1 = pnts.get(a-1).getX();
	    double X2 = pnts.get(b-1).getX();
	    double Y1 = pnts.get(a-1).getY();
	    double Y2 = pnts.get(b-1).getY();
	    
		double dist = Math.sqrt((X1-X2)*(X1-X2)+(Y1-Y2)*(Y1-Y2));
		return dist;

	}




}
