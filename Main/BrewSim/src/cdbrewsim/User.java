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

import org.json.JSONObject;

public class User {
	String username;
	String password;
	GameState current;
	String challengeQuestion;
	String challengeResponse;
	
	public User(String user, String pass) throws FileNotFoundException
	{
		username = user;
		password = pass;
		
	}
	public User(JSONObject obj){
		username = obj.getString("username");
		password = obj.getString("password");
		challengeQuestion = obj.getString("Question");
		challengeResponse = obj.getString("Response");
	}
	public String getChallengeResponse(){
		return(challengeResponse);
	}
	public boolean isUser(String user){
	    return(user.compareTo(username)==0);
	}
	public boolean isPass(String pass){
	    return(password.compareTo(pass)==0);
	}
	public boolean setChallengeResponse(String response){
		challengeResponse=response;
		return(true);
	}
	public String getChallengQuestion(){
		return(challengeQuestion);
	}
	public boolean setChallengeQuestion(String question){
		challengeQuestion=question;
		return(true);
	}
	public GameState getGameState(){
		return(current);
	}
	public boolean setGameState(GameState save){
		current = save;
		return(true);
	}
	public boolean newGameState(GameState overwrite){
		current = overwrite;
		return(true);
	}
	public String getPassword(){
		return(password);
	}
	public boolean setPassword(String pass){
		password=pass;
		return(true);
	}
	public String getUsername(){
		return(username);
	}
	public boolean setUsername(String user){
	    	username = user;
	    	return(true);
		
	}
	public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();
		obj.put("username", this.username);
		obj.put("password", this.password);
		obj.put("Question", this.challengeQuestion);
		obj.put("Response", this.challengeQuestion);
		return(obj);
	}
}
