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

public class Brewing {
	private final Recipe recipe;
	private final BeerStyle style;
	private final int IBU_WEIGHT = 7;		// Every IBU off style, takes this percent off IBU score.
	private final int ABV_WEIGHT = 30;		// Every percent off style, takes this percent off ABV score.
	private final int COLOR_WEIGHT = 20;	// Every SRM unit off style, takes this percent off Color score.	
	private final int SCORE_FACTORS = 3;	// Number of factors being averaged for overall score.
	private final double BATCH_SIZE = Grain.BATCH_SIZE; // Standardize all calculations to be 5 gallon batch.
	
	Brewing(Recipe recipe, BeerStyle style){
		this.recipe = recipe;
		this.style = style;}
	
	private double calculateIBUperHop(Hop hop){
		// Does all the calculations to find out IBU of a hop addition.
		double IBU = 0;
		
		double gravityOfBoil = this.calculateGravityOfBoil();
		double fG = 1.65 * Math.pow(0.00125, gravityOfBoil - 1);
		double fT = (1-Math.pow(Math.E, (-0.04*hop.getTime()))) / 4.15 ;
		double utilization = fG * fT;
		// IBU = AAU * U * C / V
		IBU = (hop.getAAU() * utilization * 74.89) / BATCH_SIZE;
		return IBU;}
	
	private double calculateGravityOfBoil(){
		double gravity = 0;
		for(Grain grain: recipe.getGrains()){
			gravity += grain.getSpecificGravity();}
		gravity/=1000;
		gravity+=1;
		return gravity;}
	
	private double calculateABVperGrain(Grain grain){
		double ABV = 0;
		
		// FG = Final Gravity = 1 + ((OG-1) * (1 - Attenuation Percent))
		double FG = 1 + ((grain.getSpecificGravity()-1) * (1 - recipe.getYeast().getApparentAttenuation()));
		// ABV = (OG - FG) *0.129
		ABV  = (grain.getSpecificGravity() - FG) * 0.129;
		return ABV;}
	
	public int getBitterScore(){
		// returns a score 0-100
		int score = 100;
		double IBU = 0;
		
		// use helper method to calculate total IBU
		for(Hop hop: recipe.getHops()){
			IBU += calculateIBUperHop(hop);}
		
		// check beer style against this recipe's IBU, deduct points if outside range
		if(IBU<style.getMinBitterness())
			score -= (int)((style.getMinBitterness()-IBU)* IBU_WEIGHT);
		else if(style.getMaxBitterness()<IBU)
			score -= (int)((IBU - style.getMaxBitterness()) * IBU_WEIGHT);
		if(score < 0)
			return 0;
		return score;}
	
	public int getAbvScore(){
		// returns a score 0-100
		int score = 100;
		double ABV = 0;
		
		// use helper method to calculate total ABV
		for(Grain grain: recipe.getGrains()){
			ABV += calculateABVperGrain(grain);}
		
		// check beer style against this recipe's ABV, deduct points if outside range
		if(ABV<style.getMinABV())
			score -= (int)((style.getMinABV()-ABV) * ABV_WEIGHT);
		else if(style.getMaxABV()<ABV)
			score -= (int)((ABV - style.getMaxABV()) * ABV_WEIGHT);
		if(score < 0)
			return 0;
		return score;}
	
	public int getColorScore(){
		// returns a score 0-100
		int score = 100;
		double SRM = 0;
		
		// use helper method to calculate total ABV
		for(Grain grain: recipe.getGrains()){
			SRM += grain.getSRMcolor();}
		
		// check beer style against this recipe's ABV, deduct points if outside range
		if(SRM<style.getMinColor())
			score -= (int)((style.getMinColor()-SRM) * COLOR_WEIGHT);
		else if(style.getMaxColor()<SRM)
			score -= (int)((SRM - style.getMaxColor()) * COLOR_WEIGHT);
		if(score < 0)
			return 0;
		return score;}
	
	public int getBrewScore(){
		int score = 0;
		// currently just averaging the scores together. Will want to weight them. Want more factors, add later.
		score = (this.getAbvScore() + this.getBitterScore() + this.getColorScore()) / SCORE_FACTORS;
		return score;}
	
	public Recipe getRecipe(){
		return this.recipe;}
	
	public BeerStyle getBeerStyle(){
		return this.style;}
}
