import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringMatchingRABIN_KARP extends StringMatchingAbs implements
		StringMatching {
	private static final long MAX_VALUE = 2147483647;//
	private char[] alphabet;

	public StringMatchingRABIN_KARP(String motif) throws IOException {
		super(motif);
		alphabet = alphabet(this.P, this.T);
		sort(alphabet);
	}

	@Override
	public void matching() {
		long[] ti = null;// new long [this.getN()-this.getM()+1];
		ti = ti();
		long p = decimal(this.P, 0);
		if(ti!=null){
			for (int i = 0; i < ti.length; i++) {
			if (p == ti[i]) {
				this.addPositionValide(i);
			}
		}
		}
		
	}

	private long decimal(char[] p, int k) {
		long d = alphabet.length;
		long result = 0;
		if (alphabet.length * p.length < MAX_VALUE) {
			for (int i = 0; i < this.getM(); i++) {
				result += Math.pow(d, this.getM() - 1 - i)
						* decimalChar(p[k + i]);
			}
			return result;
		}
		return -1;
	}

	private int decimalChar(char c) {
		for (int i = 0; i < this.alphabet.length; i++) {
			if (alphabet[i] == c)
				return i;
		}
		return -1;
	}

		private long[] ti() {
		int d = this.alphabet.length;// degre de codage exemple si d=10 alors on
										// est dansle cas de codage decimal,d=16
										// hexadecimal
										// et dans des cas quelconques,
										// prevus,d=nombre de caractere de T et
										// P sans repetition
		if(this.getN() >= this.getM()){
			long[] ti = new long[this.getN() - this.getM()];
			ti[0] = decimal(T, 0);
			for (int i = 1; i < ti.length; i++) {
				ti[i] = (long) ((d * ti[i - 1])
						- (Math.pow(d, this.getM()) * decimalChar(this.T[i - 1])) + (decimalChar(T[i
						+ this.getM() - 1])));
			}
			return ti;
			
		}
		else{
			return null;
			
		}
	}

	/*****************************************************************************************************/
	private char[] alphabet(char[] p, char[] t) {
		char[] alphabet = null;
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < p.length; i++)
			if (!(exist(p[i], temp))) {
				temp.add(new Integer((int) (p[i])));
			}
		for (int i = 0; i < t.length; i++)
			if (!(exist(t[i], temp))) {
				temp.add(new Integer((int) (t[i])));
			}
		alphabet = new char[temp.size()];
		for (int i = 0; i < temp.size(); i++)
			alphabet[i] = (char) (temp.get(i)).intValue();
		return alphabet;
	}

	private boolean exist(char c, List<Integer> t) {
		for (int i = 0; i < t.size(); i++)
			if ((int) c == (t.get(i)).intValue())
				return true;
		return false;
	}


	private void sort(char[] table) {

		Arrays.sort(table);

	}
}
