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

public class Brew {
	String name;
	Brewing brewing;
	double timeToSpoil;
	int brewScore;
	int brewRank;
	int currentQuantity;
	double currentPrice;
	//We can set different constructors here. I don't know when we will need what. 
	public Brew(String name, Brewing brewing, double timeToSpoil, int brewRank, int currentQuantity, double currentPrice){
		this.name = name;
		this.brewing = brewing;
		this.timeToSpoil = timeToSpoil;
		this.brewScore = brewing.getBrewScore();
		this.brewRank = brewRank;
		this.currentQuantity = currentQuantity;
		this.currentPrice = currentPrice;
	}
	
	public InvItem[] getIngredients(){
		return brewing.getRecipe().getIngredients();}
	
	public double getTimeToSpoil(){
		return this.timeToSpoil;}
	
	public boolean setTimeToSpoil(double time){
		this.timeToSpoil = time;
		if(this.timeToSpoil == time)
			return true;
		return false;}
	
	public int getBrewScore(){
		return brewScore;}
	
	public int getBrewRank(){
		return this.brewRank;}
	
	public boolean setBrewRank(int rank){
		this.brewRank = rank;
		if(this.brewRank == rank)
			return true;
		return false;}
	
	public double getCurrentQuantity(){
		return this.currentQuantity;}

	public boolean addQuantity(double more){
		double oldQuantity = this.currentQuantity;
		this.currentQuantity += more;
		if(this.currentQuantity == oldQuantity + more)
			return true;
		return false;}
	
	public boolean removeQuantity(double less){
		double oldQuantity = this.currentQuantity;
		this.currentQuantity -= less;
		if(this.currentQuantity == oldQuantity - less)
			return true;
		return false;}
	
	public double getCurrentPrice(){
		return this.currentPrice;}
	
	public boolean setCurrentPrice(double newPrice){
		this.currentPrice = newPrice;
		if(this.currentPrice == newPrice)
			return true;
		return false;}
}
