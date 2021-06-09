
public class TargetWord implements Comparable<TargetWord>{
	private String str;
	private double count;
	public TargetWord(String str, double count) {
		super();
		this.str = str;
		this.count = count;
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}

	@Override
	public int compareTo(TargetWord o) {
		if(this.getCount()<o.getCount()) {
			return -1;
		}else if(this.getCount()>o.getCount()) {
			return 1;
		}else {
		return 0;
		}
	}
	
}
