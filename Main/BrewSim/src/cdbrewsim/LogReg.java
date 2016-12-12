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

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

public class LogReg {
    //Checks if it a user and if not it gets added to the database. 
    public String addUsername(String username, String pass) throws FileNotFoundException{
	
	Boolean isname = Database.isUser(username);
	String toset = username;
	System.out.println(isname);
	if(isname){
	    isname=false;
	    return("{\"Boolean\":"+isname.toString()+"}");
	}
	else
	{
	   User newuser = new User(username, pass);
	   Database.addUser(newuser);
	   GameState playerstate = new GameState(0,1,500.00);
	   newuser.setGameState(playerstate);
	   isname = true;
	   System.out.println(toset);
	   Database.exportJson();
	   return("{\"Boolean\":"+isname.toString()+",\"Name\":\""+toset+"\"}");
	}
    }
    //To attemp a login
    public String login(String username,String pass){
	
	Boolean firstcheck = Database.isUser(username);
	if(!firstcheck)
	{
	    return("{\"Boolean\":"+firstcheck.toString()+"}");
	}
	User checkuser = Database.getUser(username);
	firstcheck = checkuser.isPass(pass);
	if(!firstcheck)
	{
	    return("{\"Boolean\":"+firstcheck.toString()+"}"); 
	}
	firstcheck = true;
	return("{\"Boolean\":"+firstcheck.toString()+",\"Name\":\""+username+"\"}"); 
    }
    //To check if the username exist. 
    public String isUser(String user){
	Boolean firstcheck = Database.isUser(user);
	if(!firstcheck){
	    return("{\"Boolean\":"+firstcheck.toString()+",\"Name\":\""+user+"\"}");
	}
	else
	{
	    User togetchallenge = Database.getUser(user);
	    String challenge = togetchallenge.getChallengQuestion();
	    return("{\"Boolean\":"+firstcheck.toString()+",\"Name\":\""+user+"\",\"Question\":\""+challenge+"\"}");
	}
	
    }
    //To check the Challenge Question. 
    public String checkChallenge(String user, String resp){
	User checkuser = Database.getUser(user);
	String response = checkuser.getChallengeResponse();
	if(response.compareTo(resp)==0)
	{
	    Boolean check = true;
	    String pass = checkuser.getPassword();
	    return("{\"Boolean\":"+check.toString()+",\"Pass\":\""+pass+"\"}");
	}
	else
	{
	    Boolean check = false;
	    return("{\"Boolean\":"+check.toString()+"}");
	}
    }
    //To add challenge Question
    public String addChallenge(String user, String challenge, String response) throws FileNotFoundException{
	Boolean firstcheck = Database.isUser(user);
	if(!firstcheck)
	{
	    return("{\"Boolean\":"+firstcheck.toString()+"}");
	}
	User checkuser = Database.getUser(user);
	checkuser.setChallengeQuestion(challenge);
	checkuser.setChallengeResponse(response);
	firstcheck = true;
	Database.exportJson();
	return("{\"Boolean\":"+firstcheck.toString()+"}");
    }
    //To Return the gamestate of the user
    public String getgamestate(String user){
    	String packed = "";
    	User current = Database.getUser(user);
    	GameState currentgame = current.getGameState();
    	JSONObject gamedata = currentgame.toJson();
    	packed = gamedata.toString();
    	return(packed);
    	
    }
    //Return inventory Items. Not using user right now, but will need it 
    //for when we adjust the ingredient list by their rank. 
    public String getIngredients(){
    	 
    	List<InvItem> list = new LinkedList<InvItem>();
    	JSONObject ingredients  =new JSONObject();
    	list = Database.getIngredients();
    	int x = 0;
    	for(InvItem each : list){
    		
    		ingredients.put("Ingredient"+x,each.getJson());
    		x++;
    	}
    	return(ingredients.toString());
    }
    public String getRecipes(){
   	 
    	List<Recipe> list = new LinkedList<Recipe>();
    	JSONObject recipes  =new JSONObject();
    	list = Database.getRecipes();
    	int x = 0;
    	for(Recipe each : list){
    		
    		recipes.put("Recipe"+x,each.getJson());
    		x++;
    	}
    	return(recipes.toString());
    }
    public String getStyles(){
      	 
    	List<BeerStyle> list = new LinkedList<BeerStyle>();
    	JSONObject styles  =new JSONObject();
    	list = Database.getStyles();
    	int x = 0;
    	for(BeerStyle each : list){
    		
    		styles.put("Recipe"+x,each.toJson());
    		x++;
    	}
    	return(styles.toString());
    }
    public String getUserRecipes(String username){
    	JSONObject recipes = new JSONObject();
    	User current = Database.getUser(username);
    	GameState userstate = current.getGameState();
    	List<Recipe> userlist = new LinkedList<Recipe>();
    	userlist = userstate.getRecipes();
    	int x = 0;
    	for(Recipe each : userlist){
    		
    		recipes.put("Recipe"+x,each.getJson());
    		x++;
    	}
    	return(recipes.toString());
    }
    public String getUserInv(String username){
    	JSONObject inventory = new JSONObject();
    	User current = Database.getUser(username);
    	GameState userstate = current.getGameState();
    	List<InvItem> userlist = new LinkedList<InvItem>();
    	userlist = userstate.getInventory();
    	int x = 0;
    	for(InvItem each : userlist){
    		
    		inventory.put("Ingredient"+x,each.getJson());
    		x++;
    	}
    	return(inventory.toString());
    }
    public boolean purchase(String user, String itemname, String amount, String price){
    	double newamount = Double.parseDouble(amount);
    	double newprice = Double.parseDouble(price);
    	User current = Database.getUser(user);
    	List<InvItem> buyitem = Database.getIngredients();
    	List<Recipe> buyrecipe = Database.getRecipes();
    	GameState userstate = current.getGameState();
    	double money = userstate.getBalance();
    	money = money-newprice;
    	userstate.setBalance(money);
    	System.out.println(userstate.getBalance());
    	InvItem ingredient;
    	Recipe buyrecipe2;
    	for(InvItem each : buyitem)
    	{
    		if(each.getName().compareTo(itemname)==0)
    		{
    			ingredient = new InvItem(each);
    			each.setAmount(each.getAmount()-newamount);
    			ingredient.setAmount(newamount);
    			userstate.setIventory(ingredient);
    			
    			
    		}
    	}
    	for(Recipe each : buyrecipe)
    	{
    		if(each.getName().compareTo(itemname)==0)
    		{
    			buyrecipe2 = new Recipe(each);
    			
    			userstate.setRecipe(buyrecipe2);
    		}
    	}
    	 	
    	return(true);
    }
}
