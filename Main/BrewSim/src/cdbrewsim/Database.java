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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Database {
	static List<User> users = new LinkedList<User>();
	static List<Recipe> recipes = new LinkedList<Recipe>();
	static List<InvItem> ingredients = new LinkedList<InvItem>();
	static List<Equipment> tools = new LinkedList<Equipment>();
	static List<BeerStyle> styles = new LinkedList<BeerStyle>();
	
	
	public static List<BeerStyle> getStyles() {
		return styles;
	}
	public static void setStyles(List<BeerStyle> styles) {
		Database.styles = styles;
	}
	public static boolean setStyle(BeerStyle style){
		styles.add(style);
		return(true);
	}
	public static boolean addUser(User name){
		users.add(name);
		return(true);
	}
	public static  boolean isUser(String username){
		for(User check: users)
		{
		    if(check.getUsername().compareTo(username)==0)
		    {
			return(true);
		    }
		}
		return(false);
	}
	
	//Will implement when we know what these json files will look like. 
	public static  boolean importJson(){
		try{
			FileInputStream in = new FileInputStream("data/userinfo.json");
			JSONObject obj = new JSONObject(new JSONTokener(in));
			String[] names = JSONObject.getNames(obj);
			for(int i=0; i<names.length;i++)
			{
				JSONObject obj1 = new JSONObject();
				obj1 = obj.getJSONObject(names[i]);
				JSONObject obj2 = new JSONObject();
				obj2 = obj1.getJSONObject("gamestate");
				User eachuser = new User(obj1.getJSONObject("user"));
				GameState eachgamestate = new GameState(obj2);
				eachuser.setGameState(eachgamestate);
				users.add(eachuser);
				
			}
		}
		catch(Exception ex) {
			System.out.println("Exception importing from json: " + ex.getMessage());
		}
		try{
			FileInputStream instyle = new FileInputStream("data/styles.json");
			JSONObject obj = new JSONObject(new JSONTokener(instyle));
			String[] names = JSONObject.getNames(obj);
			System.out.println("whenhere");
			System.out.println(names[0]);
			for(int i=0; i<names.length;i++)
			{
				JSONObject obj1 = new JSONObject();
				
				obj1 = obj.getJSONObject(names[i]);
				System.out.println(obj1.toString());
				BeerStyle style = new BeerStyle(obj1);
				System.out.println(style.toString());
				styles.add(style);
				
			}
		}
		catch(Exception ex) {
			System.out.println("Exception importing from json: " + ex.getMessage());
		}
		try{
			FileInputStream inrecipe = new FileInputStream("data/recipes.json");
			 
			JSONObject obj = new JSONObject(new JSONTokener(inrecipe));
			String[] names = JSONObject.getNames(obj);
			System.out.println(obj.toString());
			for(int i=0; i<names.length;i++)
			{
				JSONObject obj1 = new JSONObject();
				
				obj1 = obj.getJSONObject(names[i]);
				
				Recipe recipe = new Recipe(obj1);
				
				recipes.add(recipe);
				
			}
		}
		catch(Exception ex) {
			System.out.println("Exception importing from json: " + ex.getMessage());
		}
		try{
			FileInputStream iningredients = new FileInputStream("data/ingredinfo.json");
			 
			JSONObject obj = new JSONObject(new JSONTokener(iningredients));
			String[] name = JSONObject.getNames(obj);
			System.out.println(obj.toString());
			for(int x=0; x<name.length;x++)
			{
				if(obj.getJSONObject(name[x]).getString("category").compareTo("Hop")==0)
				{
					System.out.println("here?");
					
					System.out.println("after test");
					ingredients.add(new Hop(obj.getJSONObject(name[x])));
					System.out.println("afterhere?");
				}
				else if(obj.getJSONObject(name[x]).getString("category").compareTo("Grain")==0)
				{
					ingredients.add(new Grain(obj.getJSONObject(name[x])));
				}
				else if(obj.getJSONObject(name[x]).getString("category").compareTo("Yeast")==0)
				{
					ingredients.add(new Yeast(obj.getJSONObject(name[x])));
				}
				else
				{
					ingredients.add(new InvItem(obj.getJSONObject(name[x])));
					
				}
			}
		}
		catch(Exception ex) {
			System.out.println("Exception importing from json: " + ex.getMessage());
		}
		return(true);
	}
	//Will implement when we know what these json files will look like. 
	public static boolean exportJson() throws FileNotFoundException{
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		int g = 0;
		for(User each: users)
		{
			obj1.put("user", each.toJson());
			obj1.put("gamestate", each.getGameState().toJson());
			obj2.put("player"+g, obj1);
		}
		PrintWriter out = new PrintWriter("data/userinfo.json");
		out.println(obj2.toString());
		out.close();
		JSONObject obj3 = new JSONObject();
		System.out.println(ingredients.toString());
		int z = 1;
		for(InvItem each: ingredients)
		{
			each.getJson().toString();
			obj3.put("ingredients"+z, each.getJson());
			z++;
		}
		PrintWriter ingredOut = new PrintWriter("data/ingredinfo.json");
		ingredOut.println(obj3.toString());
		ingredOut.close();
		JSONObject obj4 = new JSONObject();
		int b =0;
		for(Recipe each: recipes){
			obj4.put("recipe"+b, each.getJson());
			b++;
		}
		PrintWriter recipeOut = new PrintWriter("data/recipes.json");
		recipeOut.println(obj4.toString());
		recipeOut.close();
		JSONObject obj5 = new JSONObject();
		int c = 0;
		for(BeerStyle each: styles){
			obj5.put("style"+c, each.toJson());
			c++;
		}
		PrintWriter styleout = new PrintWriter("data/styles.json");
		styleout.println(obj5.toString());
		styleout.close();
		return(true);
	}
	public static User getUser(String username){
		for(User check: users)
		{
		    if(check.getUsername().compareTo(username)==0)
		    {
			return(check);
		    }
		}
		return(null);
	}
	public static List<Recipe> getRecipes(){
		return(recipes);
	}
	//Shouldn't need the one below if we import from json, but for testing. 
	public static boolean setRecipes(List<Recipe> rec){
		recipes = rec;
		return(true);
	}
	public static boolean setRecipe(Recipe rec){
		recipes.add(rec);
		return(true);
	}
	public static List<InvItem> getIngredients(){
		return(ingredients);
	}
	//Shouldn't need the one below if we import from json, but for testing. 
	public static boolean setIngredients(List<InvItem> ingred){
		ingredients = ingred;
		return(true);
	}
	public static boolean setIngredient(InvItem ingred){
		ingredients.add(ingred);
		return(true);
	}
	public static List<Equipment> getEquipment(){
		return(tools);
	}
	public static boolean setEquipment(List<Equipment> ntools){
	    	tools = ntools;
	    	return(true);
	}
}

