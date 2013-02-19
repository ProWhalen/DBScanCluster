package mihir.visualize;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import mihir.dbscan.data.TwoDimPoint;;

@SuppressWarnings("serial")
public class Visualization extends JFrame {
	
	public Visualization(int height, int width) {
		super("Clustering Algorithms Visualization");
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
	}
	
	public void plotClusters(ArrayList<TwoDimPoint> points, int dotWidth,
			int dotHeight) {
		this.add(new ClustersPanel(points, dotWidth, dotHeight));
	}
}
