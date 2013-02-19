package mihir.dbscan.getdatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mihir.dbscan.data.*;;
//only reads two d files now
public class ReadCSV {
	
	String path;
	
	public ReadCSV(String path) {
		// Initialize A CSV file to be stored in memory as vector of points
		this.path = path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public ArrayList<TwoDimPoint> ReadTable() throws FileNotFoundException,IOException
	{
		ArrayList<TwoDimPoint>allPoint=new ArrayList<>();
		FileReader fr     = new FileReader (path);
		BufferedReader  br    = new BufferedReader(fr);
		
		String line;
		while((line=br.readLine())!=null)
		{
			try {
				
			
			String [] array = line.split(",");
			TwoDimPoint tdp = new TwoDimPoint(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
			allPoint.add(tdp);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("corrupt file ");
				br.close();
				fr.close();
				System.exit(0);
			}
		}
		
		br.close();
		fr.close(); 
		return allPoint;
		
	}
	
	

}
