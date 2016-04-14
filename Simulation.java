import java.io.IOException;
import java.util.List;

public class Simulation {
	private StringMatching sm;
	private String algorithmName;
	private String usesPattern;
	private long timeOfExecution;
	private long startTime;
	private long endTime;
	private List<Integer> validPositions;

	public boolean run() throws IOException, InterruptedException {
		StringMatching sm = getStringMatching(algorithmName, usesPattern);
		setStartTime(System.nanoTime());
		sm.matching();
		setEndTime(System.nanoTime());
		validPositions = sm.getPatternValidPositions();
		return true;
	}
	
	public List<Integer> getValidPositions() {
		return validPositions;
	}

	public void setValidPositions(List<Integer> validPositions) {
		this.validPositions = validPositions;
	}
	public void showValidPosition() {
		if (this.validPositions.isEmpty()) {
			System.out.println("aucune occurence trouv�");
		} else {
			for (int i = 0; i < validPositions.size(); i++) {
				System.out.println(validPositions.get(i).toString());
			}
		}
	}

	private StringMatching getStringMatching(String algorithmName,
			String usesPattern2) throws IOException, InterruptedException {
		if (algorithmName.equals("NATIVE"))
			return new StringMatchingNATIVE(usesPattern);
		if (algorithmName.equals("RABIN_KARP"))
			return new StringMatchingRABIN_KARP(usesPattern);
		if (algorithmName.equals("KMP"))
			return new StringMatchingKMP(usesPattern);
		if (algorithmName.equals("SuffixTree"))
			return new StringMatchingSuffixTree(usesPattern);
		
		return null;

	}

	public Simulation(String Pattern, String algoName) throws IOException,
			InterruptedException {
		algorithmName = algoName;
		usesPattern = Pattern;
		sm = this.getStringMatching(algoName, Pattern);
		setStartTime(0);
		setEndTime(0);

	}

	/**
	 * @return the algorithmName
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * @param algorithmName
	 *            the algorithmName to set
	 */
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	/**
	 * @return the usesPattern
	 */
	public String getUsesPattern() {
		return usesPattern;
	}

	/**
	 * @param usesPattern
	 *            the usesPattern to set
	 */
	public void setUsesPattern(String usesPattern) {
		this.usesPattern = usesPattern;
	}

	/**
	 * @return the timeOfExecution
	 */
	public long getTimeOfExecution() {
		return timeOfExecution;
	}

	/**
	 * @param timeOfExecution
	 *            the timeOfExecution to set
	 */
	public void setTimeOfExecution(long timeOfExecution) {
		this.timeOfExecution = timeOfExecution;
	}

	/**
	 * @return the sm
	 */
	public StringMatching getSm() {
		return sm;
	}

	/**
	 * @param sm
	 *            the sm to set
	 */
	public void setSm(StringMatching sm) {
		this.sm = sm;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
