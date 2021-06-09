import java.util.ArrayList;



public class Words {
	private String firstTwoWords;
	private ArrayList<TargetWord> targetWord = new ArrayList<>();
	private double count=0;
	public Words(String firstTwoWords, ArrayList<TargetWord> targetWord, double count) {
		super();
		this.firstTwoWords = firstTwoWords;
		this.targetWord = targetWord;
		this.count = count;
	}
	
	public Words() {
		super();
	}

	public String getFirstTwoWords() {
		return firstTwoWords;
	}
	public void setFirstTwoWords(String firstTwoWords) {
		this.firstTwoWords = firstTwoWords;
	}
	public ArrayList<TargetWord> getTargetWord() {
		return targetWord;
	}
	public void setTargetWord(ArrayList<TargetWord> targetWord) {
		this.targetWord = targetWord;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	
}