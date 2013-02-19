package mihir.visualize;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ClusteringLogger {
	
	private static final boolean PRINT_LOG = true;
	private static final boolean ALLOW_INFO = true;
	private static final boolean ALLOW_DEBUG = true;
	private static final boolean ALLOW_ERROR = true;
	private static final boolean ALLOW_EXCEP = true;
	private static final boolean ALLOW_FATAL = true;
	
	public static final int INFO = 0;
	public static final int DEBUG = 1;
	public static final int ERROR = 2;
	public static final int EXCEP = 3;
	public static final int FATAL = 4;
	
	public static void out(Object o, String msg, int type) {
		if (PRINT_LOG) {
			switch (type) {
			case INFO:
				if (ALLOW_INFO) {
					System.out.println("[INFO] >>> " + msg);
					System.out.println("Class: " + o.getClass().toString());
					System.out.println();
				}
				break;

			case DEBUG:
				if (ALLOW_DEBUG) {
					System.out.println("[DEBUG] >>> " + msg);
					System.out.println("Class: " + o.getClass().toString());
					System.out.println();
				}
				break;
				
			case ERROR:
				if (ALLOW_ERROR) {
					System.out.println("[ERROR] >>> " + msg);
					System.out.println("Class: " + o.getClass().toString());
					System.out.println();
				}
				break;

			case EXCEP:
				if (ALLOW_EXCEP) {
					System.out.println("[EXCEPTION] >>> " + msg);
					System.out.println("Class: " + o.getClass().toString());
					System.out.println();
				}
				break;

			case FATAL:
				if (ALLOW_FATAL) {
					System.out.println("[FATAL] >>> " + msg);
					System.out.println("Class: " + o.getClass().toString());
					System.out.println();
				}
				break;

			default:
				break;
			}
		}
	}
	
	public static String getStackTrace(Throwable ex) {
		PrintStream ps = new PrintStream(new ByteArrayOutputStream());
		ex.printStackTrace(ps);
		String trace = ps.toString();
		ps.close();
		return trace;
	}
}
