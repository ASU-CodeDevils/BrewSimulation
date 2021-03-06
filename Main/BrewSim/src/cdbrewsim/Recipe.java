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

public class Recipe {
	final static int MAX_HOPS = 7;
	final static int MAX_GRAINS = 5;
	String name;
	InvItem [] ingredients;
	double price;
	int level;
	
	public Recipe(String name, InvItem[] ingredients, int level,double price){
		this.name = name;
		this.ingredients = ingredients;
		this.level = level;
		this.price = price;
		this.trimIngredientArray();}
	
	public Recipe(Recipe another){
		this.name = another.name;
		this.ingredients =another.ingredients;
		this.level = another.level;
		this.price = another.price;
		this.trimIngredientArray();}
	
	public Recipe(JSONObject obj){
		this.name = obj.getString("name");
		this.level = obj.getInt("level");
		this.price = obj.getDouble("price");
		String[] name = JSONObject.getNames(obj);
		int y = 0;
		ingredients = new InvItem[name.length-3];
		for(int x =0;x<name.length;x++){
			if(name[x].indexOf("ingredient")!=-1){
				JSONObject test = new JSONObject();
				test = obj.getJSONObject(name[x]);
			
				if(obj.getJSONObject(name[x]).getString("category").compareTo("Hop")==0){
					ingredients[y] = new Hop(test);
				}
				else if(obj.getJSONObject(name[x]).getString("category").compareTo("Grain")==0){
					ingredients[y] = new Grain(obj.getJSONObject(name[x]));}
				else if(obj.getJSONObject(name[x]).getString("category").compareTo("Yeast")==0){
					ingredients[y] = new Yeast(obj.getJSONObject(name[x]));
				}
				else{
					ingredients[y] = new InvItem(obj.getJSONObject(name[x]));
				}
				y++;
			}
		}
	}
	public String getName(){
		return this.name;}
	
	public int getNumberOfIngredients(){
		return this.ingredients.length;
	}
	
	private void trimIngredientArray(){
		// after construction, trims the ingredient array if needed. No more issues with null pointer.
		int length = 0;
		for(InvItem item: this.ingredients){
			if(item == null)
				break;
			length++;
		}
		InvItem[] temp = new InvItem[length];
		for(int i = 0; i<length; i++){
			temp[i] = ingredients[i];
		}
		ingredients = temp;
	}
	
	public Hop[] getHops(){
		int counter = 0;
		Hop[] hops;
		// count number of hops
		for(InvItem item: ingredients){
			if(item instanceof Hop)
				counter++;
		}
		// if less than max hops, array size is counter, else max hops
		if(counter<MAX_HOPS)
			hops = new Hop[counter];
		else
			hops = new Hop[MAX_HOPS];
		// reset counter
		counter = 0;
		// fill array with hops, if more hops than max hops, return at max
		for(InvItem item: ingredients){
			if(item instanceof Hop){
				hops[counter++] = (Hop)item;
				if(counter == MAX_HOPS)
					return hops;
			}
		}
		return hops;}
	
	public Grain[] getGrains(){
		int counter = 0;
		Grain[] grains;
		// count number of grains
		for(InvItem item: ingredients){
			if(item instanceof Grain)
				counter++;
		}
		// if less than max grains, array size is counter, else max grains
		if(counter<MAX_GRAINS)
			grains = new Grain[counter];
		else
			grains = new Grain[MAX_HOPS];
		// reset counter
		counter = 0;
		// fill array with grains, if more grains than max grains, return at max
		for(InvItem item: ingredients){
			if(item instanceof Grain){
				grains[counter++] = (Grain)item;
				if(counter == MAX_GRAINS)
					return grains;
			}
		}
		return grains;}
	
	public Yeast getYeast(){
		// intentionally only returns first yeast. There should be only one.
		for(InvItem item: ingredients){
			if(item instanceof Yeast)
				return (Yeast)item;
		}
		return null;}
	
	public InvItem[] getIngredients(){
		return this.ingredients;}
	
	public boolean setIngredients(InvItem[] nIngredients){
		ingredients = nIngredients;
		if(ingredients.equals(nIngredients))
			return true;
		return false;}
	
	//careful here. Going to need to copy array to add. Thanks, this should be ok.
	public boolean addIngredient(InvItem ingredient){
		int oldLength = ingredients.length;
		InvItem[] temp = new InvItem[ingredients.length+1];
		for(int i = 0; i<ingredients.length;i++)
			temp[i] = ingredients[i];
		temp[temp.length-1] = ingredient;
		ingredients = temp;
		if(ingredients.length == oldLength+1)
			return true;
		return false;}
	
	public int getLevel(){
		return this.level;}
	
	public boolean setLevel(int level){
		this.level = level;
		return true;
	}
	public boolean setName(String name){
		this.name = name;
		return true;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(InvItem item: ingredients){
			if(item == null)
				return s.toString();
			s.append(item.toString() +"\n");
		}
		return s.toString();
	}
	
	public JSONObject getJson(){
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("price", this.price);
		System.out.println("152");
		int b = 1;
		for(int x = 0;x<ingredients.length;x++){
			System.out.println(ingredients[x].getName());
			System.out.println(ingredients[x].toString());
			obj.put("ingredient"+b, ingredients[x].getJson());
			b++;
		}
		obj.put("level", this.level);
		return(obj);
	}
}
