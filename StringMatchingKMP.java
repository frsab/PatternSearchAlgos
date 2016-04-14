import java.io.IOException;

public class StringMatchingKMP extends StringMatchingAbs implements
		StringMatching {
	private int[] pi;


	public StringMatchingKMP(String motif) throws IOException,
			InterruptedException {
		super(motif);
		this.pi = null;

	}

	@Override
	public void matching() throws InterruptedException {
		pi = this.pi();
		int q = 0; // nombre de caractere concordants
		for (int i = 0; i < T.length; i++) {
			while (q > 0 && P[q] != T[i])
				q = pi[q];// Prochain caractère ne concorde pas.
			if (P[q] == T[i])
				q++;// Prochain caractère concorde
			if (q == this.getM()) {
				// System.out.println("Votre motif est detecter dans la position "+(i-this.getM())+
				// " du texte ");
				this.addPositionValide(i + 1 - this.getM());
				q = this.pi[q];
			}
		}
	}

	public int[] pi() throws InterruptedException {
		int[] result = new int[getPattern().length() + 1];
		result[1] = 0;
		for (int i = 2; i <= getPattern().length(); i++) {
			String p_i = getPattern().substring(0, i);
			result[i] = getPi(p_i);
		}
		return result;
	}

	private int getPi(String p) {
		for (int q = (p.length() - 1); q > 0; q--) {
			if (p.startsWith(p.substring(p.length() - q))) {
				return q;
			}
		}
		return 0;
	}

}
