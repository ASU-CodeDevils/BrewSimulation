package cdbrewsim;

public class Hop extends InvItem {
	double alphaAcid;
	int time;
	public Hop(String name, String description, String category, double amount, String graphic, double price, double alphaAcid, int time) {
		// amount should be in oz with increments of 0.1 oz.
		// time is minutes in boil.
		super(name, description, category, amount, graphic, price);
		this.alphaAcid = alphaAcid;
		this.time = time;
		this.setCategory("Hop");
	}
	public Hop(String name, double amount, double alphaAcid, int time){
		super(name, amount);
		this.alphaAcid = alphaAcid;
		this.time = time;
		this.setCategory("Hop");
	}
	
	public double getAAU(){
		double AAU = amount * alphaAcid;
		return AAU;
	}
	public double getAplphaAcid(){
		return this.alphaAcid;
	}
	public int getTime(){
		return this.time;
	}
	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(this.alphaAcid + " ");
		s.append(this.time + " ");
		return s.toString();
	}
}