package cdbrewsim;

public class Yeast extends InvItem {
	double AA;
	public Yeast(String name, String description, String category, double amount, String graphic, double price, double apparentAttenuation) {
		super(name, description, category, amount, graphic, price);
		this.AA = apparentAttenuation;
	}
	
	public double getAA(){
		return this.AA;
	}

}
