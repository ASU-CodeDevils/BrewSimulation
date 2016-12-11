package cdbrewsim;

import java.util.LinkedList;
import java.util.List;

public class BrewTesting {
	static InvItem[] ingredients = new InvItem[20];
	static Recipe recipe;
	static BeerStyle style;
	static Brewing brewing;
	static Brew brew;
	/*
	Classic American Pale Ale
	(5 gallons/19 L, all-grain)
	OG = 1.055 FG = 1.014
	IBU = 53 SRM = 8 ABV = 5.3%

	Ingredients
	11 lbs. (5.0 kg.) 2-row pale malt
	0.5 lbs. (0.22 kg) crystal malt (40 °L)
	11 AAU Cascades hops (bittering)(2 oz./56 g at 5.5% alpha acid)
	11 AAU Cascades hops (flavor)(2 oz./56 g at 5.5% alpha acids)
	Wyeast 1056 (American Ale) or White Labs WLP001 (California Ale) yeast
	1 cup corn sugar (for priming)*/
	public static void test(){
		// Load ingredients into ingredient array.
		List<InvItem> list = new LinkedList<InvItem>();
		list.add(new Yeast("WLP001 California Ale", 0.75));
		list.add(new Grain("2-row Pale Malt", 11.0, 37, 1.8));
		list.add(new Grain("Crystal Malt 40°", 0.5, 34, 40));
		list.add(new Hop("Cascade 5.5AA", 1.8, 5.5, 60));
		list.add(new Hop("Cascade 5.5AA", 2.0, 5.5, 10));
		
		//	Database.setIngredients(list);
		
		
		// Create recipe
		//recipe = new Recipe("Classic American Pale Ale", ingredients, 1);
		//style = new  BeerStyle("American Pale Ale", 20, 50, 3, 14, 4.2, 6.2);
		
		List<Recipe> listrecipe = Database.getRecipes();
		System.out.println(listrecipe.size());
		System.out.println("Here at recipe");
		for(Recipe each: listrecipe){
			
			if(each.getName().compareTo("Classic American Pale Ale")==0)
			{
				recipe = each;
			}
			 
			//System.out.println(recipe.toString());
			 
		}
		// Establish style characteristics
		//style = new  BeerStyle("American Pale Ale", 20, 50, 3, 14, 4.2, 6.2);
		List<BeerStyle> list2 = Database.getStyles();
		for(BeerStyle each: list2){
			if(each.getName().compareTo("American Pale Ale")==0) //was returning the wrong style when checking if not -1. Style names have a lot of similarities.
			{
				style = each;
			}
		}
		//Database.setRecipe(recipe);
		//Database.setStyle(style);
		System.out.println(style.name.toString());
		brewing = new Brewing(recipe, style);
		brew = new Brew("Testing", brewing);
		System.out.println(recipe.toString());
		System.out.println(recipe.getNumberOfIngredients());
		System.out.println(brewing.getAbvScore());
		System.out.println("The beer's ABV is " + brewing.getABV());
		System.out.println(brewing.getBitterScore());
		System.out.println("The beer's IBU is " + brewing.getIBU());
		System.out.println(brewing.getColorScore());
		System.out.println("The beer's SRM (color) is " + brewing.getColor());
		System.out.println(brewing.getBrewScore());
	}
}
