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
    public String getIngredients(String user){
    	 
    	List<? extends InvItem> list = new LinkedList<InvItem>();
    	JSONObject ingredients  =new JSONObject();
    	list = Database.getIngredients();
    	for(InvItem each : list){
    		if(each instanceof Grain)
    		{
    			ingredients.put("Ingredient",((Grain) each).getJson());
    		}
    	}
    	return(ingredients.toString());
    }
}
