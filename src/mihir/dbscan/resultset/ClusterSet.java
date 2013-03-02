/**
 * 
 */
package mihir.dbscan.resultset;

import java.util.ArrayList;

/**
 * @author mihir
 *
 */
public class ClusterSet {
	
	ArrayList<Cluster>clus ;
	public ClusterSet() {

		clus = new ArrayList<Cluster>(20) ;
	}
	public void setClus(ArrayList<Cluster> clus) {
		this.clus = clus;
		
	}
	public ArrayList<Cluster> getClus() {
		return clus;
	}
	public void AppendToQueue(Cluster p)
	{
		clus.add(p);
	}

}
