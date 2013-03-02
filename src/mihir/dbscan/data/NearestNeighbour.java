/**
 * 
 */
package mihir.dbscan.data;

/**
 * @author mihir
 *
 */
public class NearestNeighbour {

	 int Id;
	 double dist;
	 /**
	 * 
	 */
	public NearestNeighbour(int Id,double dist) {
		// TODO Auto-generated constructor stub
		this.Id=Id;
		this.dist=dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the dist
	 */
	public double getDist() {
		return dist;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

}
