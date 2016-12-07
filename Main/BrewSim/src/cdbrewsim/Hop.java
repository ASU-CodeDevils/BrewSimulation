package cdbrewsim;

public class Hop extends InvItem {
	double AA;
	public Hop(String name, String description, String category, double amount, String graphic, double price, double alphaAcid) {
		super(name, description, category, amount, graphic, price);
		this.AA = alphaAcid;
	}
	
	public double getAAU(){
		double AAU = amount * AA;
		return AAU;
	}

}
