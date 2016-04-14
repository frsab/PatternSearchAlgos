import java.io.IOException;

public class StringMatchingNATIVE extends StringMatchingAbs implements
		StringMatching {

	public StringMatchingNATIVE(String motif) throws IOException {
		super(motif);
	}

	@Override
	public void matching() {
		for (int i = 0; i < (T.length - P.length + 1); i++) {
			for (int j = 0; j < P.length; j++) {
				if (P[j] != T[i + j])
					break;
				else if (j == (P.length - 1)) {
					// System.out.println("Votre motif est detecter dans la position "+i+
					// " du texte ");
					this.addPositionValide(i);
				}
			}
		}
	}

}
