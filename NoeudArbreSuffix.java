import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

public class NoeudArbreSuffix {
	private static final int MAX_CHAR_ASCCI = 255;
	private static final int MEGABYTE = (1024*1024);
	private NoeudArbreSuffix[] children;
	private List<Integer> indexes;

	public NoeudArbreSuffix() { 
		indexes = new ArrayList<Integer>();
		this.children = new NoeudArbreSuffix[MAX_CHAR_ASCCI];

	}

	void insertSuffix(String suffix, int index){
		  indexes.add(new Integer(index));
		    if (suffix.length() > 0)
		    {
		        char cIndex = suffix.charAt(0);
		        if (children[cIndex] == null)  
		        	children[cIndex] = new NoeudArbreSuffix();
		        
		        try {
					children[cIndex].insertSuffix(suffix.substring(1), index+1);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        catch (OutOfMemoryError e) {
		        	 MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
					MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
		            long maxMemory = heapUsage.getMax() / MEGABYTE;
		            long usedMemory = heapUsage.getUsed() / MEGABYTE;
		            System.out.println(" : Memory Use :" + usedMemory + "M/" + maxMemory + "M");
		        }
		    }
	}
	
	public List<Integer> search(String s) {
	    if (s.length() == 0)        return indexes;
	    if (children[s.charAt(0)] != null)      return (children[s.charAt(0)]).search(s.substring(1));
	    else return null;
	}

}
