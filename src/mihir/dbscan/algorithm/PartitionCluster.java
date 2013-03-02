/**
 * 
 */
package mihir.dbscan.algorithm;


import java.util.ArrayList;
import mihir.dbscan.data.ClusterInfo;
import mihir.dbscan.data.TwoDimPoint;



/**
 * @author mihir
 *This class contains function to do clustering and send no of clusters formed.
 *It takes two parameters
 *1)points to be taken for clustering
 *2)points to be removed from cluster
 */
public class PartitionCluster {

	ArrayList<TwoDimPoint>points;
	private static final int NOT_VISITED = 0;
	public static final int NOISE = -2000000;
	double epsilon;
	int minPts;
	int minClusterSize;
	int clusterId;
	int noOfCluster;
	
	public PartitionCluster(double epsilon,int minPts,int minClusterSize,ArrayList<ClusterInfo>const_CIpoints,ArrayList<TwoDimPoint>constPnts)  {
		
		this.epsilon=epsilon;
		this.minPts=minPts;
		this.minClusterSize=minClusterSize;
		this.noOfCluster = 1;
		clusterId=0;
		points = new ArrayList<>();
		for(ClusterInfo ci: const_CIpoints)
		{
			TwoDimPoint tdp = new TwoDimPoint(ci.getId(), constPnts.get(ci.getId()-1).getX(), constPnts.get(ci.getId()-1).getY(), 
					NOT_VISITED, constPnts.get(ci.getId()-1).getNearestList(), 
					constPnts.get(ci.getId()-1).getNoOfNearestNeighbour(), 0);
			points.add(tdp);
			
		}
		
	}
	
	public void Cluster() {
		int n = points.size();
		int cluster = 0;
		// now scan the points in the data-set point by point 
		for (int i = 0; i < n; i++) {
			TwoDimPoint p = points.get(i);
			if (p.getClusterId() == NOT_VISITED) {
				// get the nearest points to the point 'p'
				// within the range defined by radius 'epsilon'
				ArrayList<TwoDimPoint> nearest = p.getNearestList();
				if (nearest.size() < minClusterSize) {
					p.setClusterId(NOISE);
				} else {
					// the point 'p' has sufficient number of neighbors 
					// to form its own cluster, now we need to expand that cluster
					// to include the points that are directly-reachable by
					// the points in the neighborhood of 'p'
					cluster++;
					clusterId=cluster;
					System.out.println(clusterId);
					p.setClusterId(cluster);
					ExpandCluster(nearest, p.getId(), cluster);
				}
			}
		}
		
	}
	

	private void ExpandCluster(ArrayList<TwoDimPoint> nearest, int id, int currClusterID) {
		
		while (!nearest.isEmpty()) {
			TwoDimPoint currentPtn = nearest.remove(0);
			
			if (currentPtn.getClusterId() == NOT_VISITED) {
				// check if the point can be a core point
				// then add its adjacency list to the 'nearest' list.
				ArrayList<TwoDimPoint> adj = 
					currentPtn.getNearestList();
				if (adj.size() >= minClusterSize) {
					nearest.addAll(adj);
				}
				// add this current point to the current cluster
				currentPtn.setClusterId(currClusterID);
			}
			
			if (currentPtn.getClusterId() == NOISE) {
				currentPtn.setClusterId(currClusterID);
			}
		}	
	}
 
	/**
	 * @return the clusterId
	 */
	public int getClusterId() {
		return clusterId;
	}
	

}
