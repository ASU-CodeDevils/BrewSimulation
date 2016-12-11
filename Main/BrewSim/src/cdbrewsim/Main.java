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

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONString;
import org.json.JSONObject;
import org.json.JSONTokener;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Method;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

//import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) throws SecurityException, Exception {

    //port(Integer.valueOf(System.getenv("PORT")));
	  port(9091);
	  //Below is for testing. 
	 /* String test = "{\"Class\":\"Main\",\"Method\":\"test\"}";
	  JSONObject method = new JSONObject(test);
	  System.out.println(method.toString());
	  String[] names = JSONObject.getNames(method);
	  System.out.println(names[0]);
	  String result = reflectflip(method, names);*/
		  
	  staticFileLocation("/public");
	  Database.importJson();
	  
	  //Testing below
	 
	  
	  BrewTesting.test();
	  Database.exportJson();
	  //End Test
	  
	  
	  post("/meth",(req,res)->{
		  System.out.println("receiving request");
		  System.out.println(req.body().toString());
		  JSONObject method = new JSONObject(req.body().toString());
		  System.out.println(method.toString());
		  String[] names = JSONObject.getNames(method);
		  System.out.println(names[0]);
		  String result = reflectflip(method, names);
		   return(result);
		  
	      
	  });
    
  }   
  
  
  public static String reflectflip(JSONObject meth, String[] names) throws Exception, SecurityException{
             String aClass ="";
             String aMethod="";
             String result = "";
             String paramstring ="";
             int paramint =0;
             double paramdouble=0.0;
             boolean parambool = false ;
             String ifone = "";
             customParameter[] para = new customParameter[names.length-2];
             
            // List<customParameter> para = new LinkedList<customParameter>();
             @SuppressWarnings("rawtypes")
	    Class params[] = new Class[names.length-2];
           
             
             // we assume that called methods have no argument
             for(int z = 0;z<names.length;z++)
             {
        	 if(names[z].compareTo("Method")==0)
        	 {
        	     aMethod = meth.getString("Method");
        	     System.out.println(aMethod);
        	 }
        	 else if(names[z].compareTo("Class")==0)
        	 {
        	     aClass  = "cdbrewsim." + meth.getString(names[z]);
        	     System.out.println(aClass);
        	 }
        	 else
        	 {
        	     int count = 0;
        	     if(names[z].compareTo("param1")==0)
        		 count=1;
        	     else if(names[z].compareTo("param2")==0)
        		 count=2;
        	     else if(names[z].compareTo("param3")==0)
        		 count=3;
        	     else if(names[z].compareTo("param4")==0)
        		 count=4;
        	     else if(names[z].compareTo("param5")==0)
        		 count=5;
        	     else if(names[z].compareTo("param6")==0)
        		 count=6;
        	     else if(names[z].compareTo("param7")==0)
        		 count=7;
        	     System.out.println("here");
        	     
        	     System.out.println(count);
        		 Object check = meth.get(names[z]);
        		 if(check instanceof Integer){
        		    customParameter temp = new customParameter();
        		    temp.customSet(meth.getInt(names[z]));
        		     para[count] = temp;
        		     params[count] = Integer.class;
        		     if(names.length==3){
        			 paramint = meth.getInt(names[z]);
        			 ifone = "int";
        		     }
        		 }
        		 else if(check instanceof String){
        		     customParameter temp = new customParameter();
         		    temp.customSet(meth.getString(names[z]));
         		     para[count] = temp;
         		     params[count] = String.class;
         		    System.out.println("String");
         		   if(names.length==3){
         		       paramstring = meth.getString(names[z]);
         		       ifone = "string";
         		   }
        		 }
        		 else if(check instanceof Double){
        		     customParameter temp = new customParameter();
         		    temp.customSet(meth.getDouble(names[z]));
         		   para[count] = temp;
         		   params[count] = Double.class;
         		  if(names.length==3){
        		       paramdouble = meth.getDouble(names[z]);
        		       ifone = "double";
        		   }
        		 }
        		 else 
        		 {
        		     customParameter temp = new customParameter();
         		    temp.customSet(meth.getBoolean(names[z]));
         		   para[count] = temp;
         		   params[count]= Boolean.class;
         		  if(names.length==3){
           		       parambool = meth.getBoolean(names[z]);
           		       ifone = "bool";
           		   }
        		 }
        		 
        	     
        	 }
             }
            
           
            //System.out.println(params[0].toString());
           
            //System.out.println(para[0].getparam() instanceof String);
            //System.out.println(para[0].getparam() instanceof Integer);
            //System.out.println(para[0].getparam() instanceof Boolean);
            //System.out.println(para[0].getparam() instanceof Double);
            //String test = para[0].getparam();
            // get the Class
            Class<?> thisClass = Class.forName(aClass);
            // get an instance
            Object iClass = thisClass.newInstance();
            // get the method
            Method thisMethod = thisClass.getDeclaredMethod(aMethod, params);
            // call the method
            
            //System.out.println(para[0].getparam().toString());
            switch(para.length){
            case 0: result = thisMethod.invoke(iClass, null).toString();
            		break;
            //case 1: result = thisMethod.invoke(iClass, para[0].getparam()).toString();
    	    //			break;
            case 1: break;
            case 2: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam()).toString();
    				break;
            case 3: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam()).toString();
    				break;
            case 4: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam(), para[3].getparam()).toString();
    				break;
            case 5: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam(), para[3].getparam(), para[4].getparam()).toString();
					break;
            case 6: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam(), para[3].getparam(), para[4].getparam(), para[5].getparam()).toString();
					break;
            case 7: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam(), para[3].getparam(), para[4].getparam(), para[5].getparam(), para[6].getparam()).toString();
					break;
            case 8: result = thisMethod.invoke(iClass, para[0].getparam(), para[1].getparam(), para[2].getparam(), para[3].getparam(), para[4].getparam(), para[5].getparam(), para[6].getparam(), para[7].getparam()).toString();
					break;
            
            }
            if(ifone.compareTo("")!=1)
            {
        	if(ifone=="string")
        	{
        	    result = thisMethod.invoke(iClass, paramstring).toString();
        	}
        	else if(ifone=="int")
        	{
        	    result = thisMethod.invoke(iClass, paramint).toString();
        	}
        	else if(ifone=="double")
        	{
        	    result = thisMethod.invoke(iClass, paramdouble).toString();
        	}
        	else if(ifone=="bool")
        	{
        	    result = thisMethod.invoke(iClass, parambool).toString();
        	}
            }
            System.out.println(result);
            return(result);
        }
  public static String jsonPack(String retValue){
	 
	   String result = "{\"Return\":\""+ retValue+ "\"}";
	   return(result);
	   
  }

}

