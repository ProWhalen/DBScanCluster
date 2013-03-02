/**
 * 
 */
package mihir.dbscan.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import mihir.dbscan.algorithm.DbScanRank;
import mihir.dbscan.algorithm.Dbscan;
import mihir.dbscan.algorithm.PartitionCluster;
import mihir.dbscan.data.ClusterInfo;
import mihir.dbscan.data.NearestNeighbour;
import mihir.dbscan.data.TwoDimPoint;
import mihir.dbscan.resultset.ClusterSet;
import mihir.visualize.Visualization;

/**
 * @author mihir
 *
 */
public class MainWithClusterScoring {

	static Dbscan db;
	static ArrayList<TwoDimPoint>pnts;
	static ClusterSet clusterSet;
	private static int noOfCluster;
	private static HashMap<Integer, ArrayList<Integer>>clusMap;
	private static final double epsilon=6;
	private static final int minPts=6;
	private static final int minClusterSize=3;
	private static final int SIZEOFDATASET=10000;
	private static ArrayList<ArrayList<ClusterInfo>>clusterInfo;//contains all the points of a cluster 
    private static ArrayList<Integer>pntsNotTakenCluster;
    
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		DoPrimitiveClustering();
		clusMap=new HashMap<Integer, ArrayList<Integer>>();
		SetClus(pnts);
		clusterInfo = new ArrayList<>();
		Iterator iterator = clusMap.keySet().iterator();
		int i=0;
		while(iterator.hasNext())
		{
			Integer key = Integer.parseInt(iterator.next().toString());
			ArrayList<Integer>cluster = new ArrayList<>();
			cluster=clusMap.get(key);

			clusterInfo.add(SetNearestList(cluster));
			//System.out.println(i+"  :  "+clusterInfo.get(i).size());
			++i;
			
		}
           
		
		/**
		 * Try dbscan on each cluster.
		 * code for doing dbscan on each cluster use loop 1 parameter,
		 * dbscan will send only no of clusters
		 */
		for(i=0;i<clusterInfo.size();i++)
		{
			ArrayList<ClusterInfo> cluster=new ArrayList<>();//single cluster =--- loop 1
			cluster=clusterInfo.get(i);
		    
		    PartitionCluster pc = new PartitionCluster(epsilon, minPts, minClusterSize,cluster , pnts);
			System.out.println(pc.getClusterId());
			break; 
		}
		
		//Example access all the points of a cluster
		/*for(i=0;i<clusterInfo.size();i++)//cluster list
		{
			
			ArrayList<ClusterInfo> cluster=new ArrayList<>();//single cluster =--- loop 1
			cluster=clusterInfo.get(i);

			 for(int j=0;j<cluster.size();j++)
			{
				ClusterInfo tmpClusterInfo = cluster.get(j);//single point
				
				
                 ArrayList<Integer>nearPnts = tmpClusterInfo.getNearList();
                System.err.println(tmpClusterInfo.getId()+"\n");
				for(int k=0;k<nearPnts.size();k++)
				{
					System.out.println(nearPnts.get(k));//single nearest point of each point

				}
				
				
			}
			 
		}*/



	}
	/**
	 * It fixes the nearest points of a cluster according to its distance from point
	 * @param cluster all the points of a cluster
	 */
	private static ArrayList<ClusterInfo> SetNearestList(ArrayList<Integer> cluster) {

		ArrayList <ClusterInfo>clusterArray = new ArrayList<>();
		for (int i=0;i<cluster.size();i++)
		{
			int id=cluster.get(i);
			ArrayList<NearestNeighbour> nearList = new ArrayList<>();

			for(int j=0;j<cluster.size();j++)
			{
				double dist = ClusterInfo.EuclideanDistance(cluster.get(i), cluster.get(j), pnts);
				if(dist<=epsilon)
				{
					NearestNeighbour nn = new NearestNeighbour(cluster.get(j), dist) ;
					nearList.add(nn);
				}

			}
			ArrayList<Integer>nearListSorted=SortArraylist(nearList);
			ClusterInfo clus = new ClusterInfo();
			clus.setId(id);
			clus.setNearList(nearListSorted);
			clusterArray.add(clus);

		}

		return clusterArray;

	}
	/**
	 * @param nearList
	 * @return tmpList Contains nearest neighbours of a point in sorted order
	 */
	private static ArrayList<Integer> SortArraylist(
			ArrayList<NearestNeighbour> nearList) {
		ArrayList<Integer>tmpList = new ArrayList<>();

		Collections.sort(nearList,new Comparator<NearestNeighbour>()
				{

			@Override
			public int compare( NearestNeighbour p1,
					NearestNeighbour p2) {
				return Double.valueOf(p1.getDist()).compareTo(Double.valueOf(p2.getDist()));
			}


				});
		for(int i=0;i<nearList.size();i++)
		{
			tmpList.add(nearList.get(i).getId());
		}
		return tmpList;
	}
	public static void DoPrimitiveClustering() throws FileNotFoundException, IOException
	{

		db = new Dbscan(6, 6, 3, "chameleon.csv");
		db.Cluster();
		pnts=db.getPoints();
		//Visualization visual = new Visualization(750, 1250);
		//visual.plotClusters(pnts, 5, 5);
		//sort the pnts for further processing.To get Id
		Collections.sort(pnts,new Comparator<TwoDimPoint>()
				{

			@Override
			public int compare(TwoDimPoint p1,
					TwoDimPoint p2) {
				return Integer.valueOf(p1.getId()).compareTo(Integer.valueOf(p2.getId()));
			}


				});


	}

	public static void SetClus(ArrayList<TwoDimPoint> points)
	{
		int i=0;
		while(i<points.size())
		{

			int clusterNumber=points.get(i).getClusterId();

			if(clusterNumber>0)
			{
				//System.out.println(clusMap.containsKey(clusterNumber));
				if(clusMap.containsKey(clusterNumber))
				{
					ArrayList<Integer>tmp = clusMap.get(clusterNumber);
					tmp.add( points.get(i).getId());
					clusMap.put(clusterNumber, tmp);


				}
				else
				{
					ArrayList<Integer>tmp= new ArrayList<>();
					tmp.add( points.get(i).getId());
					clusMap.put(clusterNumber, tmp);


				}
			}
			i++;

		}
	}


}
