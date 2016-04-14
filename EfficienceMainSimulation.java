import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EfficienceMainSimulation {
	
	public static void main(String[] args) throws IOException,InterruptedException {
		String dir=realTimeDirectoryName();
		new File("rep").mkdir(); 
		File fileResult = new File (dir+"trace.tr");		
		File filetrace = new File (dir+"moyTrace.tr");
		
		
		
		String[] AlgorithmName = { "NATIVE", "RABIN_KARP", "KMP","SuffixTree"};
		PrintWriter pwAll[] = new  PrintWriter[AlgorithmName.length];// (tracefileName);

		String[] motif =randomMotif(100,"ACGT");
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (fileResult)));	
		PrintWriter pwTrace = new PrintWriter (new BufferedWriter (new FileWriter (filetrace)));	
		System.out.println(fileResult);
		init(pwAll,AlgorithmName,dir);
runsimulation(motif, AlgorithmName,100,pw,pwTrace,pwAll);

	}
		
	

	private static void init(PrintWriter[] p,String[] algos, String dir ) throws IOException {
		for(int i=0;i< p.length;i++){
			String directoryFileName=dir+"_trace"+algos[i]+".tr";
			FileWriter fw=new FileWriter (directoryFileName);
			p[i]=new PrintWriter (new BufferedWriter (fw));	
		}
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



	private static String realTimeDirectoryName() {
		SimpleDateFormat formater = null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("yyyyMMddHHmmss");
		String dirName="trace"+(formater.format(aujourdhui))+"";
		return dirName;
	}



	

	private static boolean runsimulation(String[] motif,String[] algorithmName, int nbSimulation, PrintWriter pw, PrintWriter pwTrace, PrintWriter[] pwAll) throws IOException, InterruptedException {
		if(nbSimulation<1)return true;
		long ll=0;
		System.out.println("# CombinaisonNum	durationMoy algoNum patternLength algoName pattern");
		pw.println("# CombinaisonNum	durationMoy algoNum patternLength algoName pattern");
		pwTrace.println("# CombinaisonNum	duration algoNum patternLength algoName pattern");
		
		for (int i = 0; i < algorithmName.length; i++) {
			pw.println("# algorithme "+algorithmName[i]+"***********************************");
			pw.println("");
			for (int j = 0; j < motif.length; j++){
				Simulation s = new Simulation(motif[j],algorithmName[i]);
				long totalTime=0;
				for(int k=0;k<nbSimulation;k++){
					s.run();
					
					totalTime+=(s.getEndTime()-s.getStartTime());
					ll++;
					pwTrace.println(ll+" "+(s.getEndTime()-s.getStartTime())+"     "+i +"    "+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" "+s.getUsesPattern()+" ");
					pwAll[i].println(ll+" "+k+" "+(s.getEndTime()-s.getStartTime())+"     "+i +"    "+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" "+s.getUsesPattern()+" ");

					//1 149843 01 NATIVE A 
					
				}
				
				long moy = totalTime/nbSimulation;
				System.out.println(ll+" "+moy+" "+i+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" " 
						  +s.getUsesPattern()+" "
							);
				  
				  pw.println(ll+" "+moy+" "+i+" "+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" " 
						  +s.getUsesPattern()+" "
							);
				
				
			}
		}
		pw.close();
		pwTrace.close();
		return true;
	}
}
