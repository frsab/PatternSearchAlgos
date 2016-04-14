import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StringMatchingSuffixTree extends StringMatchingAbs implements StringMatching {

	private NoeudArbreSuffix root;
	public StringMatchingSuffixTree(String motif) throws IOException {
			
		super(motif);
		//System.out.println("StringMatchingSuffixTree(String motif)+txt");	
		String txt=this.getTexte();
		root= new NoeudArbreSuffix();
		for (int i = 0; i < txt.length(); i++){

			root.insertSuffix(txt.substring(i), i);
			
		}
			
	}
	
	 

	@Override
	public  void matching() throws InterruptedException {
		List<Integer> result = root.search(this.getPattern());

		if (result == null)
			;//System.out.println("Pattern not found");
		else {
			for (int i = 0; i < result.size(); i++) {
				this.patternValidPositions.add(result.get(i)-this.getPattern().length());
			}
		}
	}
}
