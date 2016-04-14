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

public class MonMainSimulation {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		String fileName=realTimeFileName();
		File fileResult = new File (fileName);
		String[] AlgorithmName = {"SuffixTree", "NATIVE", "RABIN_KARP", "KMP"};
		String[] motif =randomMotif(20,"ACGT");
			/*{"A","TT","GAA",
"GTCC",
"CCTAT",
"CGTGCT",
"CCGTCAG",
"ACCAACCT",
"GGATCCTTA",
"CACTCGGATG",
"CAAGGATCGTT",
"CCCCACATCCGG",
"ACGTAGTCCGTCC",
"CGTTTCCCATCGTG"} ;*/
				//randomMotif(15,"ACGT");//{ "T" ,"TT","TTTT","TGATGG", "TGATGGGTTGTTGTGT" };
	List<Simulation> simulations = simulation(motif, AlgorithmName);
		try
		{
		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (fileResult)));
	        pw.println("NumSimulation duration algoName pattern");

		    for(int j=0;j<1;j++){
				runAllSimulations(simulations, j+1,pw);
			}
			pw.close();
		}
		catch (IOException exception){
			    System.out.println ("Erreur lors de la lecture/Ecriture  : " + exception.getMessage());

		}
	
		showResultAllSimulations(simulations);
			
	}
		
	

	private static String[] randomMotif(int n, String caracters) {
		String[] motif = new String[n];
		
		for(int i=0;i<n;i++){
			motif[i]=randomString(i,caracters);
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



	private static String realTimeFileName() {
		SimpleDateFormat formater = null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName="trace"+(formater.format(aujourdhui))+".txt";
		return fileName;
	}



	private static void showResultAllSimulations(List<Simulation> simulations) {
		System.out.println("Resultats de "+simulations.size()+" simulations");
		
		for(Simulation s:simulations){
			System.out.print("\n"+s.getUsesPattern()+"   "+s.getAlgorithmName()+ "  ");
			if (s.getValidPositions()!=null){//.isEmpty()) {
				if(s.getValidPositions().size()==0){
					//System.out.println("aucune occurence trouv� pour la recherche de motif "+ s.getUsesPattern()+ "dans le texte ");
				}
				else{
					//System.out.println("Les occurence trouv� pour la recherche de motif "+ s.getUsesPattern()+ "dans le texte ");
					for (int i = 0; i < s.getValidPositions().size(); i++) {
						System.out.print(s.getValidPositions().get(i).toString()+ "_");
					}
					//System.out.println( "_");
					
				}
			}
		}
	}

	private static void runAllSimulations(List<Simulation> simulations,int i, PrintWriter pw) throws IOException, InterruptedException {
		
		for (Simulation s:simulations) {
			s.run();				
				System.out.println(i+"      "+(s.getEndTime()-s.getStartTime())+" "
						+s.getAlgorithmName()+" "
						+s.getUsesPattern()+" "
						);
				
				  pw.println(i+"      "+(s.getEndTime()-s.getStartTime())+" "
			        		+s.getAlgorithmName().length()+" "	+s.getUsesPattern().length()+" "+s.getAlgorithmName()+" "
							+s.getUsesPattern()+" "
							);
			    		 
				    		 
				
				
				
				
				
				//+" nanosecondes "+s.getAlgorithmName());
			//System.out.println((s.getEndTime()-s.getStartTime())+" nanosecondes "+s.getAlgorithmName());
				}
		
	}

	private static List<Simulation> simulation(String[] motif,String[] algorithmName) throws IOException, InterruptedException {
		List<Simulation> s = new ArrayList<Simulation>();
		for (int i = 0; i < motif.length; i++)
			for (int j = 0; j < algorithmName.length; j++) {
				s.add(new Simulation(motif[i], algorithmName[j]));

			}
		return s;
	}
}
