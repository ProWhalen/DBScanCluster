package mihir.dbscan.param;

public class DbScanParam {
	
	 double epsilon;
	 int minPts;
     int minClusterSize;
     
	 public DbScanParam(double epsilon,int minPts,int minClusterSize) {
		// TODO Auto-generated constructor stub
		this.epsilon = epsilon;
		this.minPts = minPts;
		this.minClusterSize=minClusterSize;
	}
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	public void setMinPts(int minPts) {
		this.minPts = minPts;
	}
	public double getEpsilon() {
		return epsilon;
	}
	public int getMinPts() {
		return minPts;
	}
	public void setMinClusterSize(int minClusterSize) {
		this.minClusterSize = minClusterSize;
	}
	public int getMinClusterSize() {
		return minClusterSize;
	}
	

}
