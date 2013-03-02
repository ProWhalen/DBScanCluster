package mihir.dbscan.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mihir.dbscan.algorithm.DbScanRank;
import mihir.dbscan.algorithm.Dbscan;
import mihir.dbscan.data.TwoDimPoint;
import mihir.dbscan.resultset.Cluster;
import mihir.dbscan.resultset.ClusterSet;
import mihir.visualize.Visualization;

public class MainWithRanking {

	static Dbscan db;
	static ArrayList<TwoDimPoint>pnts;
	static ClusterSet clusterSet;
	private static int noOfCluster;
	private static int rank;


	public static void main(String[] args) throws FileNotFoundException, IOException {

		DoPrimitiveClustering();
		Collections.sort(pnts,new Comparator<TwoDimPoint>()
				{

			@Override
			public int compare(TwoDimPoint p1,
					TwoDimPoint p2) {
				return Integer.valueOf(p1.getId()).compareTo(Integer.valueOf(p2.getId()));
			}


				});

		for(int i=0;i<pnts.size();i++)
		{
           //  System.out.println(pnts.get(i).getId()+"       "+pnts.get(i).getNoOfNearestNeighbour() );
			for(int j=0;j<pnts.get(i).getNearestList().size();j++)
			{
               
				boolean flag=false;
				int [] notInclude = new int[j+1];
				notInclude[0]=pnts.get(i).getId();
				int clusterNum=0;
				for(int k=1;k<notInclude.length;k++)
				{  //System.err.println(pnts.get(i).getId());
				
				notInclude[k]=pnts.get(i).getNearestList().get(k-1).getId();
				}
				DbScanRank dbr = new DbScanRank(6, 6, 3,"chameleon.csv", notInclude);
				dbr.Cluster();
				clusterNum = dbr.getNoOfClusters();
				//System.out.println(j+1+"   "+clusterNum);
				
				if(clusterNum>noOfCluster)
				{
                   // System.out.println(pnts.get(i).getId() + " noOf points " +notInclude.length+" noOfCluster"+(clusterNum-noOfCluster+1));
					flag=true;
					for(int l=0;l<notInclude.length;l++)
					{
						double rank=pnts.get(notInclude[l]-1).getRank();
						rank = rank+((clusterNum-noOfCluster+1)/notInclude.length);
						pnts.get(i).setRank(rank);
						System.out.println(rank);
					}
					
					
				}
				if(flag==true)
				{
					break;
				}

			}
		}

		//				for(int i=0;i<pnts.size();i++)
		//				{
		//					int [] notInclude = new int[1];
		//					notInclude[0]= pnts.get(i).getId();
		//					DbScanRank dbr = new DbScanRank(6, 6, 3,"chameleon.csv", notInclude);
		//					dbr.Cluster();
		//					
		//					 //System.out.println("no of minpts = "+pnts.get(i).getNoOfNearestNeighbour());
		//					  rank = dbr.getNoOfClusters();
		//					  System.out.println("X : "+pnts.get(i).getX()+"   Y :"+pnts.get(i).getY()+"   R:"+rank);
		//					 break;
		//				}


	}


	public static void DoPrimitiveClustering() throws FileNotFoundException, IOException
	{

		db = new Dbscan(6, 6, 3, "chameleon.csv");
		db.Cluster();
		pnts=db.getPoints();
		//Visualization visual = new Visualization(750, 1250);
		//visual.plotClusters(pnts, 5, 5);
		noOfCluster=db.getNoOfCluster();
		System.out.println(noOfCluster);

	}

}
