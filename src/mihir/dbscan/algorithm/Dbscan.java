package mihir.dbscan.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import mihir.dbscan.param.DbScanParam;
import mihir.dbscan.data.TwoDimPoint;
import mihir.dbscan.getdatabase.ReadCSV;
import java.util.ArrayList;


public class Dbscan {
	
	DbScanParam dbparam;//parameters for dbscan
	ArrayList<TwoDimPoint>points;
	private static final int NOT_VISITED = -1000000;
	public static final int NOISE = -2000000;
	
	public Dbscan(double epsilon,int minPts,int minClusterSize,String filePath) throws FileNotFoundException, IOException {
		// intialises parameters and input by reading csv file
		
		dbparam=new DbScanParam(epsilon, minPts,minClusterSize);
		ReadCSV readcsv = new ReadCSV(filePath);
		points= readcsv.ReadTable();	
		
		
		int t=points.size();
		for (int i = 0; i < t; i++)
			{
			  this.points.get(i).setClusterId(NOT_VISITED);
			  ArrayList<TwoDimPoint>tmp;
			  tmp=CalNearestNeighbourEps(this.points.get(i));
			  this.points.get(i).setNearestList(tmp);
			}
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
			if (p.getClusterId() == NOT_VISITED) {
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
		ArrayList<TwoDimPoint>nearest = new ArrayList<>();
		int n=points.size();
		double eps =dbparam.getEpsilon();
		for(int i=0;i<n;i++)
		{
			TwoDimPoint tmp=points.get(i);
			if(tmp.getId()!=tdp.getId())
			{
				if(TwoDimPoint.EuclideanDistance(tmp, tdp)<=eps)
				{
					nearest.add(tmp);
				}
			}
		}
		return nearest;
		
	}


	
	
	
	
	
	
	//implement DBScan Algorithm
}
