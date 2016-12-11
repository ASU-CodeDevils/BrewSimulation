package cdbrewsim;

import org.json.JSONObject;

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
	
	public BeerStyle(String name, double bitterness,double maxBitterness, double color, double maxColor, double ABV, double maxABV) {
		this.name = name;
		this.minBitterness = bitterness;
		this.maxBitterness = maxBitterness;
		this.minColor = color;
		this.maxColor = maxColor;
		this.minABV = ABV;
		this.maxABV = maxABV;}
	public BeerStyle(JSONObject obj){
		this.name = obj.getString("name");
		this.minBitterness = obj.getDouble("mbit");
		this.maxBitterness = obj.getDouble("maxbit");
		this.minColor = obj.getDouble("mincolor");
		this.maxColor = obj.getDouble("maxcolor");
		this.minABV = obj.getDouble("minABV");
		this.maxABV = obj.getDouble("maxABV");
	}
	public boolean setName(String name){
		this.name = name;
		if(this.name.equals(name))
			return true;
		return false;}
	
	public boolean setMaxBitterness(double bitterness){
		this.maxBitterness = bitterness;
		if(this.maxBitterness ==bitterness)
			return true;
		return false;}
	
	public boolean setMaxColor(double color){
		this.maxColor = color;
		if(this.maxColor ==color)
			return true;
		return false;}
	
	public boolean setMinColor(double minColor) {
		this.minColor = minColor;
		if(this.minColor==minColor)
			return true;
		return false;}
	
	public boolean setMaxABV(double ABV){
		this.maxABV = ABV;
		if(this.maxABV == ABV)
			return true;
		return false;}
	
	public boolean setMinABV(double minABV) {
		this.minABV = minABV;
		if(this.minABV == minABV)
			return true;
		return false;}

	public double getMinColor() {
		return minColor;}
	
	public double getMinABV() {
		return minABV;}
	
	public double[] getHopProfile() {
		return hopProfile;}
	
	public boolean setHopProfile(double[] hopProfile) {
		this.hopProfile = hopProfile;
		if(this.hopProfile == hopProfile)
			return true;
		return false;}
	
	public InvItem[] getNecessaryIngredients() {
		return necessaryIngredients;}
	
	public boolean setNecessaryIngredients(InvItem[] necessaryIngredients) {
		this.necessaryIngredients = necessaryIngredients;
		if(this.necessaryIngredients == necessaryIngredients)
			return true;
		return false;}
	
	public String getName() {
		return name;}
	
	public double getMaxColor() {
		return maxColor;}
	
	public double getMaxABV() {
		return maxABV;}
	
	public boolean setMinBitterness(double minBitterness) {
		this.minBitterness = minBitterness;
		if(this.minBitterness == minBitterness)
			return true;
		return false;}
	
	public double getMinBitterness(){
		return this.minBitterness;}
	
	public double getMaxBitterness(){
		return this.maxBitterness;}
	
	public JSONObject toJson(){
		JSONObject obj = new JSONObject();
		obj.put("name",this.name);
		obj.put("mbit", this.minBitterness);
		obj.put("maxbit",this.maxBitterness);
		obj.put("mincolor",this.minColor);
		obj.put("maxcolor",this.maxColor);
		obj.put("minABV",this.minABV);
		obj.put("maxABV",this.maxABV);
		return(obj);
	}
	public String toString(){
		return(name);
	}
	
	

}
