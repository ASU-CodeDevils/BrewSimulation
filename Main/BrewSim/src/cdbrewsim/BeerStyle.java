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
	
	
	

}
