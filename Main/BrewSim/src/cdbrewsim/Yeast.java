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
		s.append(apparentAttenuation + " ");
		return s.toString();
	}
	public JSONObject getJson(){
		JSONObject obj = super.getJson();
		obj.put("apparentA",this.apparentAttenuation);
		return(obj);
	}
}
