package cdbrewsim;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


import java.net.URI;
import java.net.URISyntaxException;
import java.lang.reflect.Method;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

//import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {

    //port(Integer.valueOf(System.getenv("PORT")));
	  port(9090);
    staticFileLocation("/");

    get("/hello", (req, res) ->{return "Hello World";} );

    //get("/", (req, res) -> {
    //		
   // });
    
    //        Map<String, Object> attributes = new HashMap<>();
    //        attributes.put("message", "Hello World!");

    //        return new ModelAndView(attributes, "index.ftl");
    //    }, new FreeMarkerEngine());
    get("/meth/*",(req,res)->{
            String name = req.pathInfo().toString();
            String meth = "";
            int start = name.indexOf("meth/");
            System.out.println(name);
            start += 5;
            int last = name.indexOf(")");
            meth = name.substring(start);
            start = meth.indexOf("(");
            meth = meth.substring(0,start);
            String para = "";
            start = name.indexOf("(");
            para = name.substring(start+1);
            System.out.println(para);
            last = para.indexOf(")");
            para = para.substring(0,last);
            String flipped = Main.reflectflip(meth,para);
            System.out.println(req.pathInfo().toString());
            flipped = "done(" + flipped + ")";
            return(flipped);
       
        //return("nope");
        
    });
  }
   
public static String reflectflip(String meth, String para) throws Exception, SecurityException{
        	 String aClass;
             String aMethod;
             // we assume that called methods have no argument
             Class params[] = new Class[1];
             params[0] = String.class;
             Object paramsObj[] = {};
        	aClass  = "cdbrewsim.Main";
            aMethod = meth;
            System.out.println(aMethod);
            System.out.println(para);
            // get the Class
            Class<?> thisClass = Class.forName(aClass);
            // get an instance
            Object iClass = thisClass.newInstance();
            // get the method
            Method thisMethod = thisClass.getDeclaredMethod(aMethod, params);
            // call the method
            String flipped = thisMethod.invoke(iClass, para).toString();
            return(flipped);
        }
    public static String javaflip(String toflip){
    	String flipped = "";
    	for(int x = toflip.length()-1;x>-1;x--)
    	{
    		flipped += toflip.charAt(x);
    	}
    	return(flipped);
    }

}

