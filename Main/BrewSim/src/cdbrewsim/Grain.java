package cdbrewsim;

import org.json.JSONObject;

public class Grain extends InvItem {
	private double PE; //Potential Extract (points per lb / gallon)
	private double lovibonds; // Color intensity of grain
	public static final double EXTRACT_EFFICIENCY = 0.70; // Efficiency is usually between 60%-80%. Split the difference.
	public static final double BATCH_SIZE = 5.0; // Standardize all calculations to be 5 gallon batch.
	
	public Grain(String name, String description, String category, double amount, String graphic, double price, double potentialExtract, double lovibonds) {
		// amount should be in lbs with increments of 0.1 lb.
		super(name, description, category, amount, graphic, price);
		this.PE = potentialExtract;
		this.lovibonds = lovibonds;
		this.setCategory("Grain");
	}
	public Grain(String name, double amount, double potentialExtract, double lovibonds){
		super(name, amount);
		this.PE = potentialExtract;
		this.lovibonds = lovibonds;
		this.setCategory("Grain");
	}
	
	public double getSpecificGravity(){
		// specificGravity = (lbs grain * PotentialEnergy * EXTRACT_EFFICIENCY) / 5.0gal water (batch size)
		double specificGravity = (this.amount * this.PE * EXTRACT_EFFICIENCY) / BATCH_SIZE; 
		return specificGravity;
	}
	
	public double getSRMcolor(){
		// MCU = (Weight of grain in lbs) * (Color of grain in degrees lovibond) / (volume in gallons)
		double MCU = this.amount * this.lovibonds / BATCH_SIZE;
		// SRM color = 1.4922 * (MCU * 0.6859)
		double SRMcolor = 1.4922 * MCU * 0.6859;
		return SRMcolor;
	}

	public JSONObject getgJson(){
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("description", this.description);
		obj.put("category", this.category);
		obj.put("amount",this.amount);
		obj.put("graphic", this.graphic);
		obj.put("price", this.price);
		obj.put("extract",this.PE);
		obj.put("lovibonds", this.lovibonds);
		return(obj);
		
	}


	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(this.PE + " ");
		s.append(this.lovibonds + " ");
		return s.toString();
	}
}

