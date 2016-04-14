import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Random;

public class EfficienceMainSimulationWhithoutTraceFiles {
	
	public static void main(String[] args) throws IOException,InterruptedException {
		String[] AlgorithmName = { "NATIVE", "RABIN_KARP", "KMP","SuffixTree"};
		String[] motif =randomMotif(100,"ACGT");
		runsimulation(motif, AlgorithmName,100,null,null,null);
	}

	private static String[] randomMotif(int n, String caracters) {
		String[] motif = new String[n];	
		for(int i=0;i<n;i++){
			motif[i]=randomString(i+1,caracters);
			System.out.println(motif[i]);
		}
		
		return motif;
	}



	private static String randomString(int length, String characters) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++)
		{
		Random rng=new Random();
		text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}


	private static boolean runsimulation(String[] motif,String[] algorithmName, int nbSimulation, PrintWriter pw, PrintWriter pwTrace, PrintWriter[] pwAll) throws IOException, InterruptedException {
		if(nbSimulation<1)return true;
		long ll=0;
		System.out.println("# CombinaisonNum	durationMoy algoNum patternLength algoName pattern");
		Simulation s ;
		for (int i = 0; i < algorithmName.length; i++) {
			for (int j = 0; j < motif.length; j++){
				s=null;
				System.gc();//s.free
				s= new Simulation(motif[j],algorithmName[i]);
				long totalTime=0;
				showUsedMemory();
				for(int k=0;k<nbSimulation;k++){
					s.run();
					
					totalTime+=(s.getEndTime()-s.getStartTime());
					ll++;
	
					//1 149843 01 NATIVE A 
					
				}
				
				long moy = totalTime/nbSimulation;
				System.out.println(ll+" "+moy+" "+i+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" " 
						  +s.getUsesPattern()+" "
							);
				  
				
				
			}
		}
		return true;
	}

	private static void showUsedMemory() {
	 MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
						MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
			            long maxMemory = heapUsage.getMax() / (1024*1024);
			            long usedMemory = heapUsage.getUsed() / (1024*1024);
			            System.out.println("Memory Use :" + usedMemory + "M/" + maxMemory + "M");
		
	}
}
