package cdbrewsim;

public class BeerStyle {
	String name;
	double minBitterness;
	double maxBitterness;
	double minColor;
	double maxColor;
	double minABV;
	double maxABV;
	double[] hopProfile;			// implement later
	InvItem[] necessaryIngredients;	// implement later
	public BeerStyle(String name, double bitterness, double color, double ABV) {
		this.name = name;
		this.minBitterness = bitterness;
		this.minColor = color;
		this.minABV = ABV;
	}
	
	// need setters for max values and getters for each value still.
	public boolean setMaxBitterness(double bitterness){
		this.maxBitterness = bitterness;
		if(this.maxBitterness ==bitterness)
			return true;
		return false;
	}
	public boolean setMaxColor(double color){
		this.maxColor = color;
		if(this.maxColor ==color)
			return true;
		return false;
	}
	public boolean setMaxABV(double ABV){
		this.maxABV = ABV;
		if(this.maxABV == ABV)
			return true;
		return false;
	}
	
	
	public double getMinBitterness(){
		return this.minBitterness;
	}
	public double getMaxBitterness(){
		return this.maxBitterness;
	}
	
	
	

}
