package cdbrewsim;

public class Yeast extends InvItem {
	double apparentAttenuation;
	
	public Yeast(String name, String description, String category, double amount, String graphic, double price, double apparentAttenuation) {
		// amount is not important for yeast. Will not effect calculations as it is not used.
		super(name, description, category, amount, graphic, price);
		this.apparentAttenuation = apparentAttenuation;
	}
	
	public double getApparentAttenuation(){
		return this.apparentAttenuation;
	}
	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(apparentAttenuation + " ");
		return s.toString();
	}
}
