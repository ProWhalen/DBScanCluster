package mihir.dbscan.resultset;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

import mihir.dbscan.data.TwoDimPoint;;

/**this class is a single entity of cluster.
 * Will be used by cluster analysis and breaking   */
public class Cluster {
     
	int clusterId;
	int size;
    ArrayList<TwoDimPoint>pointClus;
    
    public Cluster() {
		// TODO Auto-generated constructor stub
    	pointClus = new ArrayList<TwoDimPoint>();
	}
    public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
    public int getClusterId() {
		return clusterId;
	}
    public void setSize(int size) {
		this.size = size;
	}
    public int getSize() {
		return size;
	}
    /**
	 * @return the pointClus
	 */
	public ArrayList<TwoDimPoint> getPointClus() {
		return pointClus;
	}
	/**
	 * @param pointClus the pointClus to set
	 */
	public void setPointClus(ArrayList<TwoDimPoint> pointClus) {
		this.pointClus = pointClus;
	}
    
    public void AppendToQueue(TwoDimPoint p)
    {
    	pointClus.add(p);
    }
}
