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
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
	  Path currentRelativePath = Paths.get("");
	  staticFileLocation("/");

	  get("/meth/*",(req,res)->{
		  	String name = req.pathInfo().toString();
	        //String name = test;
	        String meth = "";
	        int start = name.indexOf("meth/");
	        System.out.println(name);
	        start += 5;
	        int last = name.indexOf(")");
	        meth = name.substring(start);
	        start = meth.indexOf("(");
	        meth = meth.substring(0,start);
	        int count = 0;
	        for(int i =0; i<name.length();i++){
	        	if(name.charAt(i)==',')
	        	{
	        		count++;
	        	}
	        }
	        String para[] = new String[0];
	        start = name.indexOf("(");
	        if((last-start)>1&&count==0){
	        	 para = new String[1];
	        	start = name.indexOf("(");
	            para[0] = name.substring(start+1);
	            System.out.println("in one");
	            last = para[0].indexOf(")");
	           
	            para[0] = para[0].substring(0,last);
	           
	        }
	        else if((last-start)>1)
	        {
	        	para = new String[count+1];
	        	System.out.println("test");
	        }
	        int begin = 0;
	        String temp = "";
	        while(begin<count+1&&count>0){
	        	
	        	start = name.indexOf("(");
	        	temp = name.substring(start+1);
	        	start = temp.indexOf(",");
	        	System.out.println(temp);
	        	para[begin] = temp.substring(0,start);
	        	start = name.indexOf(",");
	        	if(begin<count)
	        	{
		        	name = name.substring(start+1);
		        	System.out.println(name);
		        	name = "(" +name;
		        	if(name.indexOf(",")==-1)
		        	{
		        		name = name.substring(0, name.length()-1);
		        		name = name + ",";
		        	}
	        	}
	        	begin++;
	        	
	        }
	        System.out.println(meth);
	       
	        String result = Main.reflectflip(meth,para);
	        
	        return(result);
	  });
    
  }   
  public static String testclass(){
	  String test = jsonPack();
	  return(test);
  }
  public static String reflectflip(String meth, String[] para) throws Exception, SecurityException{
        	 String aClass;
             String aMethod;
             String result = "";
             // we assume that called methods have no argument
             Class params[] = new Class[para.length];
             for(int z = 0;z<para.length;z++)
             {
            	 params[z] = String.class;
             }
             Object paramsObj[] = {};
        	aClass  = "cdbrewsim.Main";
            aMethod = meth;
            System.out.println(aMethod);
            
            // get the Class
            Class<?> thisClass = Class.forName(aClass);
            // get an instance
            Object iClass = thisClass.newInstance();
            // get the method
            Method thisMethod = thisClass.getDeclaredMethod(aMethod, params);
            // call the method
            switch(para.length){
            case 0: result = thisMethod.invoke(iClass, null).toString();
            		break;
            case 1: result = thisMethod.invoke(iClass, para[0]).toString();
    				break;
            case 2: result = thisMethod.invoke(iClass, para[0], para[1]).toString();
    				break;
            case 3: result = thisMethod.invoke(iClass, para[0], para[1], para[2]).toString();
    				break;
            case 4: result = thisMethod.invoke(iClass, para[0], para[1], para[2], para[3]).toString();
    				break;
            case 5: result = thisMethod.invoke(iClass, para[0], para[1], para[2], para[3], para[4]).toString();
					break;
            case 6: result = thisMethod.invoke(iClass, para[0], para[1], para[2], para[3], para[4], para[5]).toString();
					break;
            case 7: result = thisMethod.invoke(iClass, para[0], para[1], para[2], para[3], para[4], para[5], para[6]).toString();
					break;
            case 8: result = thisMethod.invoke(iClass, para[0], para[1], para[2], para[3], para[4], para[5], para[6], para[7]).toString();
					break;
            
            }
            return(result);
        }
 
   public static String jsonPack(){
	   String meth = "Method";
	   String par = "Parameter";
	   String ret = "Return";
	   String boo = "Bool";
	   String result = "{Method:\"\", Parameter:[], Return:\"\",Bool:\"\")";
	   return(result);
	   
   }
}

