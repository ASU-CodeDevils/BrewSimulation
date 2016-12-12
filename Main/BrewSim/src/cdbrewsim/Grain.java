/*
 * Copyright (c) 2016 ASU CodeDevils

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without 
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cdbrewsim;

import org.json.JSONObject;

public class Grain extends InvItem {
	private double PE;				//Potential Extract (points per lb / gallon)
	private double lovibonds;		// Color intensity of grain
	public static final double EXTRACT_EFFICIENCY = 0.70;	// Efficiency is usually between 60%-80%. Split the difference.
	public static final double BATCH_SIZE = 5.0; 			// Standardize all calculations to be 5 gallon batch.
	
	public Grain(String name, String description, String category, double amount, String graphic, double price, double potentialExtract, double lovibonds) {
		// amount should be in lbs with increments of 0.1 lb.
		super(name, description, category, amount, graphic, price);
		this.PE = potentialExtract;
		this.lovibonds = lovibonds;
		this.setCategory("Grain");
	}
	public Grain(String name, double amount, double potentialExtract, double lovibonds){
		super(name, "","Grain",amount, "", 0.0);
		this.PE = potentialExtract;
		this.lovibonds = lovibonds;
		this.setCategory("Grain");
	}
	public Grain(JSONObject obj){
		super(obj);
		this.name = obj.getString("name");
		this.description = obj.getString("description");
		this.category = obj.getString("category");
		this.amount = obj.getDouble("amount");
		this.graphic = obj.getString("graphic");
		this.price = obj.getDouble("price");
		this.PE = obj.getDouble("extract");
		this.lovibonds = obj.getInt("lovibonds");
	}
	public Grain(Grain another)
	{
		super(another);
		this.PE = another.PE;
		this.lovibonds = another.lovibonds;
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

	public JSONObject getJson(){
		JSONObject obj =super.getJson();
		
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

