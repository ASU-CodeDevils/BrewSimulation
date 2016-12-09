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
	Recipe recipe;
	BeerStyle style;
	Brewing(Recipe recipe,BeerStyle style){
		this.recipe = recipe;
		this.style = style;
	}
	
	public int getBitterScore(){
		// returns a score 0-100
		int score = 100;
		double IBU = 0;
		for(Hop hop: recipe.getHops()){
			// convert  to IBU's
			double gravityBoil = 1.08; // temp value, needs to be calculated.
			
			
			double fG = 1.65 * Math.pow(0.000125, gravityBoil);
			double fT = (1-Math.pow(Math.E, (-0.04*hop.getTime()))) / 4.15 ;
			double utilization = 0;
			// IBU = AAU * U * C / V
			IBU = hop.getAAU() * 74.89;
			IBU+=hop.getAAU();
		}
		
		if(IBU<style.getMinBitterness())
			score -= (int)((style.getMinBitterness()-IBU)/10);
		else if(style.getMaxBitterness()<IBU)
			score -= (int)((IBU - style.getMaxBitterness())/10);
		if(score < 0)
			return 0;
		return score;
	}
	
	
	
	
}
