package cdbrewsim;

import org.json.JSONObject;

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
		super(name, "","Yeast",1,"",0.0);
		this.apparentAttenuation = apparentAttenuation;
		this.setCategory("Yeast");
	}
	public Yeast(JSONObject obj){
		super(obj);
		this.apparentAttenuation = obj.getDouble("apparentA");
	}
	public Yeast(Yeast another){
		super(another);
		this.apparentAttenuation = another.apparentAttenuation;
	}
	
	public double getApparentAttenuation(){
		return this.apparentAttenuation;
	}
	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(this.apparentAttenuation + " ");
		return s.toString();
	}
	public JSONObject getJson(){
		JSONObject obj = super.getJson();
		obj.put("apparentA",this.apparentAttenuation);
		return(obj);
	}
}
