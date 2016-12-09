package cdbrewsim;

public class Grain extends InvItem {
	double PE; //Potential Extract (points per lb / gallon)
	double lovibonds; // Color intensity of grain
	final double EXTRACT_EFFICIENCY = 0.70; // Efficiency is usually between 60%-80%. Split the difference.
	final double BATCH_SIZE = 5.0; // Standardize all calculations to be 5 gallon batch.
	
	public Grain(String name, String description, String category, double amount, String graphic, double price, double potentialExtract, double lovibonds) {
		// amount should be in lbs with increments of 0.1 lb.
		super(name, description, category, amount, graphic, price);
		this.PE = potentialExtract;
		this.lovibonds = lovibonds;
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
	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(this.PE + " ");
		s.append(this.lovibonds + " ");
		return s.toString();
	}
}