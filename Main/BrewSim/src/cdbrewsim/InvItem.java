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

public class InvItem {
	String name;
	String description;
	String category;
	double amount;
	String graphic; //to map to the graphic asset by name
	double price;
	
	public InvItem(String name, String description, String category, double amount, String graphic, double price){
		this.name = name;
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.graphic = graphic;
		this.price = price;
		
	}
	public String getName(){
		return this.name;
	}
	public boolean setName(String name){
		this.name = name;
		if(this.name.equals(name))
			return true;
		else
			return false;
	}
	public String getDescription(){
		return this.description;
	}
	public boolean setDescription(String description){
		this.description = description;
		if(this.description.equals(description))
			return true;
		else
			return false;
	}
	public String getCategory(){
		return this.category;
	}
	public boolean setCategory(String cat){
		this.category = cat;
		if(this.category.equals(cat))
			return true;
		else
			return false;
	}
	public double getAmount(){
		return this.amount;
	}
	public boolean setAmount(Double amount){
		this.amount = amount;
		if(this.amount == amount)
			return true;
		else
			return false;
	}
	public String getGraphic(){
		return this.graphic;
	}
	public boolean setGraphic(String graphicName){
		this.graphic = graphicName;
		if(this.graphic.equals(graphicName))
			return true;
		else
			return false;
	}
	public double getPrice(){
		return this.price;
	}
	public boolean setPrice(double price){
		this.price = price;
		if(this.price == price)
			return true;
		else
			return false;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(this.name + " ");
		s.append(this.description+ " ");
		s.append(this.category+ " ");
		s.append(this.amount+ " ");
		s.append(this.graphic+ " ");
		s.append(this.price+ " ");
		return s.toString();
	}
}
