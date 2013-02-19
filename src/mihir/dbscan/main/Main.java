package mihir.dbscan.main;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import mihir.visualize.*;
import mihir.dbscan.algorithm.Dbscan;
import mihir.dbscan.data.TwoDimPoint;


public class Main {

	/**
	 * @param double epsilon,int minPts,int minClusterSize,String filePath
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		    Dbscan db = new Dbscan(6, 6, 3, "chameleon.csv");
			db.Cluster();
			ArrayList<TwoDimPoint>pnts=db.getPoints();
			Visualization visual = new Visualization(750, 1250);
			visual.plotClusters(pnts, 5, 5);
			int [] clus = new int[500];
			int noise=0;
			for(int i=0;i<pnts.size();i++)
			{
				int j=pnts.get(i).getClusterId();
			    if(j>0)
				++clus[j];
			    else
			    	++noise;
			}
			for(int i=0;i<clus.length;i++)
			{
				if(clus[i]!=0)
				{
					System.out.println("Cluster Id  ["+i+"] and No of points in cluster ["+clus[i]+"]");
				}
			}
			System.out.println("Cluster Id  [Noise ] and No of points in cluster ["+noise+"]");
			System.out.println();

	}

}
