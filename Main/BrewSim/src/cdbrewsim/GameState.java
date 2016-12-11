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
import java.util.List;

import org.json.JSONObject;
public class GameState {
	int brewingScore;
	List<Recipe> recipes;
	List<InvItem> inventory;
	List<Brew> brews;
	List<Equipment> equipment;
	double balance;
	int brewingRank;
	
	public GameState(int brewsc, int brewr, double bal){
		brewingScore = brewsc;
		brewingRank = brewr;
		balance = bal;
	}
	public GameState(JSONObject obj){
		brewingScore = obj.getInt("BrewScore");
		brewingRank = obj.getInt("BrewRank");
		balance = obj.getDouble("Balance");
	}
	public int getBrewingScore(){
		return(brewingScore);
	}
	public boolean setBrewingScore(int newscore){
		brewingScore = newscore;
		if(brewingScore>1000)
			brewingRank =2;
		else if(brewingScore>2000)
			brewingRank =3;
		else if(brewingScore>3000)
			brewingRank = 4;
		else if(brewingScore>4000)
			brewingRank = 5;
		return(true);
	}
	public List<Recipe> getRecipes(){
		return(recipes);
	}
	public boolean setRecipes(List<Recipe> allrecipes){
		recipes = allrecipes;
		return(true);
	}
	//Will need to add single recipes as the game goes along. 
	public boolean setRecipe(Recipe recipe){
		recipes.add(recipe);
		return(true);
	}
	public List<InvItem> getInventory(){
		return(inventory);
	}
	public boolean setInventory(List<InvItem> items){
		inventory = items;
		return(true);
	}
	//add a single ingredient to their inventory
	public boolean setIventory(InvItem item){
		inventory.add(item);
		return(true);
	}
	public List<Brew> getBrews(){
		return(brews);
	}
	public boolean setBrews(List<Brew> beers){
		brews = beers;
		return(true);
	}
	public boolean setBrews(Brew beer){
		brews.add(beer);
		return(true);
	}
	public double getBalance(){
		return(balance);
	}
	public boolean setBalance(double bal){
		balance = bal;
		return(true);
	}
	public int getBrewRank(){
		return(brewingRank);
	}
	public boolean setBrewRank(int rank){
		brewingRank = rank;
		return(true);
	}
	public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();
		obj.put("Balance", this.balance);
		obj.put("BrewScore", this.brewingScore);
		obj.put("BrewRank", this.brewingRank);
		return(obj);
	}

}
