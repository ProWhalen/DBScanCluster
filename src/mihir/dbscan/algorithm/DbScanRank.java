/**
 * 
 */
package mihir.dbscan.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mihir.dbscan.data.TwoDimPoint;
import mihir.dbscan.getdatabase.ReadCSV;
import mihir.dbscan.param.DbScanParam;

public class DbScanRank {

	DbScanParam dbparam;//parameters for dbscan
	public static final int NOISE = -2000000;
	private static final int NOT_VISITED = -1000000;
	int noOfClusters=0;
	int initialTempId;
	int [] notInclude;
	//TwoDimPoint notInclude;
	//int index;
	ArrayList<TwoDimPoint>points;
	
	/**
	 * 
	 * @param epsilon
	 * @param minPts
	 * @param minClusterSize
	 * @param filePath
	 * @param notInclude integer array containing ids of all points not to be included in DBScan 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public DbScanRank(double epsilon,int minPts,int minClusterSize,String filePath,int [] notInclude) throws FileNotFoundException, IOException {
		// intialises parameters and input by reading csv file
		
		dbparam=new DbScanParam(epsilon, minPts,minClusterSize);
		ReadCSV readcsv = new ReadCSV(filePath);
		points= readcsv.ReadTable();	
		this.notInclude = new int [notInclude.length];
		this.notInclude=notInclude;
		// System.out.println(":::::::::::::::::::"+this.notInclude.length);
		// System.out.println("::::::::::::::::::"+this.notInclude[0]);
		 
		int t=points.size();
		for (int i = 0; i < t; i++)
			{
			  this.points.get(i).setClusterId(NOT_VISITED);
			  ArrayList<TwoDimPoint>tmp;
			  tmp=CalNearestNeighbourEps(this.points.get(i));
			  this.points.get(i).setNearestList(tmp);
			  
			}
		Collections.sort(points,new Comparator<TwoDimPoint>()
				{

					@Override
					public int compare(TwoDimPoint p1,
							TwoDimPoint p2) {
						return Integer.valueOf(p1.getId()).compareTo(Integer.valueOf(p2.getId()));
		
					}
			
			
				});
		
		//points.remove(index);
		
	}
	
	public ArrayList<TwoDimPoint> getPoints() {
		return points;
	}
	public void Cluster() {
		int n = points.size();
		int cluster = 0;
		// now scan the points in the data-set point by point 
		for (int i = 0; i < n; i++) {
			TwoDimPoint p = points.get(i);
			if (p.getClusterId() == NOT_VISITED && CheckId(p.getId(),notInclude)) {
				// get the nearest points to the point 'p'
				// within the range defined by radius 'epsilon'
				ArrayList<TwoDimPoint> nearest = p.getNearestList();
				if (nearest.size() < dbparam.getMinClusterSize()) {
					p.setClusterId(NOISE);
				} else {
					// the point 'p' has sufficient number of neighbors 
					// to form its own cluster, now we need to expand that cluster
					// to include the points that are directly-reachable by
					// the points in the neighborhood of 'p'
					cluster++;
					noOfClusters=cluster;
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
				if (adj.size() >= dbparam.getMinClusterSize()) {
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
	public ArrayList<TwoDimPoint> CalNearestNeighbourEps(TwoDimPoint tdp)
	{
		ArrayList<TwoDimPoint>nearest = new ArrayList<TwoDimPoint>();
		int n=points.size();
		double eps =dbparam.getEpsilon();
		for(int i=0;i<n;i++)
		{
			TwoDimPoint tmp=points.get(i);
			// System.out.println(notInclude[0]);
			if(tmp.getId()!=tdp.getId() && CheckId(tmp.getId(),notInclude))
			{
				if(TwoDimPoint.EuclideanDistance(tmp, tdp)<=eps)
				{
					nearest.add(tmp);
				}
			}
		}
		return nearest;
		
	}

 private boolean CheckId (int id,int [] arr_id)
 {
	 boolean flag=true;
	 for (int i=0;i<arr_id.length;i++)
	 {
		 if(arr_id[i]==id)
			 return false;
	 }
	 return true;
 }
	
 /**
 * @return the noOfClusters
 */
public int getNoOfClusters() {
	return noOfClusters;
}
	
}