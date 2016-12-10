package cdbrewsim;

public class Yeast extends InvItem {
	double apparentAttenuation;
	
	public Yeast(String name, String description, String category, String graphic, double price, double apparentAttenuation) {
		// amount is not important for yeast. Will not effect calculations as it is not used.
		super(name, description, category, 1, graphic, price);
		this.apparentAttenuation = apparentAttenuation;
		this.setCategory("Yeast");
	}
	public Yeast(String name, double apparentAttenuation) {
		// amount is not important for yeast. Will not effect calculations as it is not used.
		super(name, 1);
		this.apparentAttenuation = apparentAttenuation;
		this.setCategory("Yeast");
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
