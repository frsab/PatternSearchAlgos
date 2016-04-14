import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public abstract class StringMatchingAbs {
	
	private String texte;
	private String pattern;
	private int n;
	private int m;
	protected List<Integer> patternValidPositions;
	protected char[] P;
	protected char [] T;
	
	private static String readFile(String fileName, Charset charset) throws IOException {
		if (charset==null) {
			charset = Charset.defaultCharset();
		}
		final Reader reader = new InputStreamReader(new FileInputStream(fileName), charset);
		try {
			char[] cbuf = new char[8192];
			final StringBuilder result = new StringBuilder();
			int len; 
			while ( (len=reader.read(cbuf)) >+ 0 ) {
				result.append(cbuf, 0, len);
			}
			return result.toString();
		} finally {
			reader.close();
		}
	}
	public StringMatchingAbs(String motif) throws IOException {
			

		this.patternValidPositions=new ArrayList<Integer>();
		this.texte =readFile("files/human_seq.fa.txt",null);
		this.pattern =motif;
		this.setN(this.getN());
		this.setM(this.getM());
		P = this.getPattern().toCharArray();
		T = this.getTexte().toCharArray();
		
	}


	public String getTexte() {
		return texte;
	}
	protected void addPositionValide(int i) {
		this.patternValidPositions.add(new Integer(i));
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int getN() {
		return this.texte.length();
	}
	public void setN() {
		this.n = this.texte.length();;
	}
	public int getM() {
		return this.pattern.length();
	}
	public void setM() {
		this.m =this.pattern.length();;
	}


	/**
	 * @return the patternValidPositions
	 */
	public List<Integer> getPatternValidPositions() {
		return patternValidPositions;
	}


	/**
	 * @param patternValidPositions the patternValidPositions to set
	 */
	public void setPatternValidPositions(List<Integer> patternValidPositions) {
		this.patternValidPositions = patternValidPositions;
	}

	public void printFile() {
		System.out.println(this.getTexte());
	}
	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	/**
	 * @param m the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}


}
