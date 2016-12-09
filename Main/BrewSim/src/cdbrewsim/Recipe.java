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

public class Recipe {
	final static int MAX_HOPS = 7;
	final static int MAX_GRAINS = 5;
	String name;
	InvItem [] ingredients;
	int level;
	
	public Recipe(String name, InvItem[] ingredients, int level){
		this.name = name;
		this.ingredients = ingredients;
		this.level = level;
	}
	public String getName(){
		return this.name;
	}
	public Hop[] getHops(){
		int counter = 0;
		Hop[] hops = new Hop[MAX_HOPS];
		for(InvItem item: ingredients){
			if(item instanceof Hop)
				hops[counter++] = (Hop)item;
		}
		return hops;
	}
	public Grain[] getGrains(){
		int counter = 0;
		Grain[] grains = new Grain[MAX_GRAINS];
		for(InvItem item: ingredients){
			if(item instanceof Hop)
				grains[counter++] = (Grain)item;
		}
		return grains;
	}
	public Yeast getYeast(){
		for(InvItem item: ingredients){
			if(item instanceof Hop)
				return (Yeast)item;
		}
		return null;
	}
	public boolean setIngredients(InvItem[] nIngredients){
		ingredients = nIngredients;
		if(ingredients.equals(nIngredients))
			return true;
		return false;
	}
	//careful here. Going to need to copy array to add. 
	public boolean addIngredient(InvItem ingredient){
		
		return false;
	}
	public boolean setAmount(double[] nprices){
		
		return false;
	}
	public int getLevel(){
		return this.level;
	}
	public boolean setLevel(int level){
		this.level = level;
		return true;
	}
	public boolean changeName(String name){
		this.name = name;
		return true;
	}
	

}
