package cdbrewsim;

import org.json.JSONObject;

public class Hop extends InvItem {
	double alphaAcid;
	int time;
	public Hop(String name, String description, String category, double amount, String graphic, double price, double alphaAcid, int time) {
		// amount should be in oz with increments of 0.1 oz.
		// time is minutes in boil.
		super(name, description, category, amount, graphic, price);
		this.alphaAcid = alphaAcid;
		this.time = time;
		this.setCategory("Hop");
	}
	//>>>>>From Evan: Had to kill your two item constructor for the InvItme class. 
	//Was messing up my json. I worked around it below. 
	public Hop(String name, double amount, double alphaAcid, int time){
		super(name, "","Hop",amount,"",0.0);
		this.alphaAcid = alphaAcid;
		this.time = time;
		this.setCategory("Hop");
	}
	public Hop(JSONObject obj){
		
		super(obj);
		 
		this.name = obj.getString("name");
		this.description = obj.getString("description");
		this.category = obj.getString("category");
		this.amount = obj.getDouble("amount");
		this.graphic = obj.getString("graphic");
		 
		this.price = obj.getDouble("price");
		 
		this.alphaAcid = obj.getDouble("alphaAcid");
		 
		this.time = obj.getInt("time");
		 
	}
	
	public double getAAU(){
		double AAU = amount * alphaAcid;
		return AAU;
	}
	public double getAplphaAcid(){
		return this.alphaAcid;
	}
	public int getTime(){
		return this.time;
	}
	public String toString(){
		StringBuilder s = new StringBuilder(super.toString());
		s.append(this.alphaAcid + " ");
		s.append(this.time + " ");
		return s.toString();
	}
	public JSONObject getJson(){
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("description", this.description);
		obj.put("category", this.category);
		obj.put("amount",this.amount);
		obj.put("graphic", this.graphic);
		obj.put("price", this.price);
		obj.put("alphaAcid", this.alphaAcid);
		obj.put("time", this.time);
		return(obj);
	}	
}