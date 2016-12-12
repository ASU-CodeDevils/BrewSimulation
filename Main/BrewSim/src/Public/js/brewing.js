//Background graphic base used from ©2015-2016 TheTDChronicler
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
var sound8 = true;
var sound9 = true;
var sound10 = true;
var sound11 = true;
var sound12 = true;
var sound13 = true;
var sound14 = true;
var sound15 = true;
var sound16 = true;
var sound17 = true;
var sound20 = true;
var sound21 = true;
var currentamount =0;
var currenttime = 0;
var amountmax =0;
var grain = [];
var gramount = [];
var yeast = [];
var hop = [];
var hopamount = [];
var hoptime = [];
 
var userrecipes;
var useringredients;
var styles;
var currentstyle = -1;
var currentrecipes = -1;
var currentingredient = -1;
var name = localStorage.name;
var mainState = {
    preload: function(){
        game.load.image('brewback', 'graphics/brewback.png');
        game.load.image('brewrecipeoff', 'graphics/brewrecipeoff.png');
        game.load.image('brewrecipeon', 'graphics/brewrecipeon.png');
        game.load.image('newbrewoff', 'graphics/newbrewoff.png');
        game.load.image('newbrewon', 'graphics/newbrewon.png');
        game.load.audio('bclick','sounds/bclick.wav');
        game.load.image('redl','graphics/redl.png');
        game.load.image('redr','graphics/redr.png');
        game.load.image('reds', 'graphics/redb.png');
        game.load.image('rrw', 'graphics/rrw.png');
        game.load.image('rlw', 'graphics/rlw.png');
        game.load.image('leftarrow','graphics/leftarrow.png');
        game.load.image('leftdown', 'graphics/leftarrowdown.png');
        game.load.image('tagback', 'graphics/ingredtag.png');
        game.load.image('invback', 'graphics/binventory.png');
        game.load.image('addup', 'graphics/addup.png');
        game.load.image('adddown','graphics/adddown.png');
        game.load.image('bottle','graphics/bottleback.png');
        game.load.image('badge','graphics/badge.png');
        game.load.image('styleback','graphics/styleback.png');
        game.load.image('bwo',"graphics/bwo.png");
        game.load.image('fwo',"graphics/fwo.png");
        game.load.image('bwon',"graphics/bwon.png");
        game.load.image('fwon',"graphics/fwon.png");
        game.load.image('pad','graphics/pad.png');
        game.load.image('upoff','graphics/topoff.png');
        game.load.image('upon','graphics/upon.png');
        game.load.image('downoff','graphics/downoff.png');
        game.load.image('downon','graphics/downon.png');
        game.load.image('atroff', 'graphics/atroff.png');
        game.load.image('atron', 'graphics/atron.png');
        game.load.image('thought', 'graphics/thought.png');
    },
    create: function(){
        game.stage.backgroundColor = '#F5F1DE';
        game.physics.startSystem(Phaser.Physics.ARCADE);
        game.add.tileSprite(0,0,1200,800,'brewback');
        var pack = packJson("LogReg","getUserRecipes", name);
        getInfo(pack,this.updaterecipes);
        var pack = packJson("LogReg","getUserInv", name);
        getInfo(pack,this.updateInv);
        var pack = packJson("LogReg","getStyles");
        getInfo(pack,this.updatestyles);
        this.brewrecipeoff = game.add.sprite(251,32,'brewrecipeoff');
        this.brewrecipeoff.inputEnabled = true;
        this.brewrecipeoff.input.useHandCursor = true;
        this.brewrecipeon = game.add.sprite(251,32,'brewrecipeon');
        this.brewrecipeon.visible = false;
        this.newbrewoff = game.add.sprite(724,32,'newbrewoff');
        this.newbrewoff.inputEnabled = true;
        this.newbrewoff.input.useHandCursor = true;
        this.newbrewon = game.add.sprite(724,32,'newbrewon');
        this.newbrewon.visible = false;
        this.bclick = game.add.audio('bclick');
        this.redl = game.add.sprite(209,98,'redl');
        this.redl.inputEnabled = true;
        this.redl.input.useHandCursor = true;
        this.redr = game.add.sprite(618,98,'redr');
        this.redr.inputEnabled = true;
        this.redr.input.useHandCursor = true;
        this.reds = game.add.sprite(267,98,'reds');
        this.invrl = game.add.sprite(65,649,'redl');
        this.invrl.inputEnabled = true;
        this.invrl.input.useHandCursor = true;
        this.invrr = game.add.sprite(221,649,'redr');
        this.invrr.inputEnabled = true;
        this.invrr.input.useHandCursor = true;
        this.reds = game.add.sprite(267,98,'reds');
        this.bottleback = game.add.sprite(840,220,'bottle');
        headingtext = game.add.text(438,122, '');
        headingtext.fontSize = 24;
        headingtext.fontWeight = 'bold';
        headingtext.fill = '#FDFEFE';
        headingtext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        headingtext.anchor.set(0.5);
        
        this.invrw = game.add.sprite(221,649,'rrw');
        this.invrw.visible = false;
        this.invlw = game.add.sprite(65,649,'rlw');
        this.invlw.visible = false;
        
        this.rrw = game.add.sprite(618,98,'rrw');
        this.rrw.visible = false;
        this.rlw = game.add.sprite(209,98,'rlw');
        this.rlw.visible = false;
        
        this.back = game.add.sprite(20,40,'leftarrow');
        this.back.inputEnabled = true;
        this.back.input.userHandCursor = true;
        this.tagback = game.add.sprite(56, 206,'tagback');
        this.invback= game.add.sprite(34, 216,'invback');
        this.backdown = game.add.sprite(20,40, 'leftdown');
        this.backdown.visible = false;
        descriptiontext = game.add.text(78,380, 'Current Ingredient');
        descriptiontext.fontSize = 20;
        descriptiontext.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        descriptiontext.wordWrap= true;
        descriptiontext.wordWrapWidth = 180;
        
        this.addup = game.add.sprite(117,653,'addup');
        this.addup.inputEnabled = true;
        this.addup.input.useHandCursor = true;
        this.adddown = game.add.sprite(117,653,'adddown');
        this.adddown.visible = false;
        this.badge = game.add.sprite(950,385,'badge');
        styletext = game.add.text(971,405, 'Style');
        styletext.fontSize = 20;
        styletext.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        styletext.wordWrap= true;
        styletext.wordWrapWidth = 180;
        this.styleback = game.add.sprite(914,460, 'styleback');
        currentstyletext = game.add.text(924,472, 'Style');
        currentstyletext.fontSize = 18;
        currentstyletext.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        currentstyletext.wordWrap= true;
        currentstyletext.wordWrapWidth = 140;
        this.fwo = game.add.sprite(1075,481,'fwo');
        this.fwo.inputEnabled = true;
        this.fwo.input.useHandCursor = true;
        this.bwo = game.add.sprite(869,481,'bwo');
        this.bwo.inputEnabled = true;
        this.bwo.input.useHandCursor = true;
        this.fwon = game.add.sprite(1075,481,'fwon');
        this.fwon.visible = false;
        this.bwon = game.add.sprite(869,481,'bwon');
        this.bwon.visible = false;
        this.pad = game.add.sprite(371,220,'pad');
        notetext = game.add.text(455,262, 'Style');
        notetext.fontSize = 22;
        notetext.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        notetext.wordWrap= true;
        notetext.wordWrapWidth = 250;
        notetext2 = game.add.text(455,440, "Amount:           ing12.1oz");
        notetext2.fontSize = 22;
        notetext2.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        notetext2.wordWrap= true;
        notetext2.wordWrapWidth = 260;
        notetext3 = game.add.text(455,575, "Time:   60 Mins");
        notetext3.fontSize = 22;
        notetext3.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        notetext3.wordWrap= true;
        notetext3.wordWrapWidth = 260;
        this.auoff = game.add.sprite(550,405,'upoff');
        this.auoff.inputEnabled = true;
        this.auoff.input.useHandCursor = true;
        this.adoff = game.add.sprite(550,453,'downoff');
        this.adoff.inputEnabled = true;
        this.adoff.input.useHandCursor = true;
        this.auon = game.add.sprite(550,405,'upon');
        this.auon.visible = false;
        
        this.adon = game.add.sprite(550,453,'downon');
        this.adon.visible = false;
        //this.upoff.visible = false;
    	//this.downoff.visible = false;
        this.aduoff = game.add.sprite(691,405,'upoff');
        this.aduoff.inputEnabled = true;
        this.aduoff.input.useHandCursor = true;
        this.addoff = game.add.sprite(691,453,'downoff');
        this.addoff.inputEnabled = true;
        this.addoff.input.useHandCursor = true;
        this.aduon = game.add.sprite(691,405,'upon');
        this.aduon.visible = false;
        
        this.addon = game.add.sprite(691,453,'downon');
        this.addon.visible = false;
        
        this.tuoff = game.add.sprite(630,533,'upoff');
        this.tuoff.inputEnabled = true;
        this.tuoff.input.useHandCursor = true;
        this.tdoff = game.add.sprite(630,583,'downoff');
        this.tdoff.inputEnabled = true;
        this.tdoff.input.useHandCursor = true;
        this.tuon = game.add.sprite(630,533,'upon');
        this.tuon.visible = false;
        
        this.tdon = game.add.sprite(630,583,'downon');
        this.tdon.visible = false;
        this.atroff = game.add.sprite(495,659,'atroff');
        this.atroff.inputEnabled = true;
        this.atroff.input.useHandCursor = true;
       
        this.atron = game.add.sprite(495,659,'atron');
        this.atron.visible = false;
        this.thought = game.add.sprite(741, 450, 'thought');
        message = game.add.text(815,520, "Need at least one of each ingredient!");
        message.fontSize = 22;
        message.fill = '#8B4513';
        //descriptiontext.setShadow(1,1, 'rgba(120,0,0,0.5)',0);
        message.wordWrap= true;
        message.wordWrapWidth = 150;
        this.thought.visible = false;
        message.setText("");
        this.clearingredient();
        
    },
    update: function(){
    	console.log(game.input.mousePointer.x);
    	console.log(game.input.mousePointer.y);
    	if(this.back.input.pointerOver())
		{
    		this.backdown.visible = true;
    		console.log('Mouseover');
			if(game.input.activePointer.isDown)
				{
				if(sound7)
					{
					this.bclick.play();
					sound7=false;
					game.state.start('main');
					}
			}
		}
		else
		{
			sound7 =true;
			this.backdown.visible = false;
		}
    	if(this.brewrecipeoff.input.pointerOver())
		{
		this.brewrecipeon.visible = true;
		if(game.input.activePointer.isDown)
        {
            if(sound1)
            	{
            	 
            	this.bclick.play();
            	 sound1 = false;
              	}
        }
		
		}
	else
		{
		this.brewrecipeon.visible = false;
		sound1 = true;
		}
	if(this.newbrewoff.input.pointerOver())
	{
		this.newbrewon.visible = true;
		if(game.input.activePointer.isDown)
        {
            if(sound2)
            	{
            	this.bclick.play();
            	 currentamount = 0;currentamount =0;
            	 currenttime = 0;
            	 amountmax =0;
            	 grain = [];
            	 gramount = [];
            	 yeast = [];
            	 hop = [];
            	 hopamount = [];
            	 hoptime = [];
            	 this.clearingredient();
            	 sound2 = false;
            	 
            	}
        }
	}
	else
	{
		this.newbrewon.visible = false;
		sound2 = true;
	}
	if(this.redr.input.pointerOver())
	{
		this.rrw.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound8)
            		{
            		
            		var items = Object.keys(userrecipes);
           		 
            		if(currentrecipes<items.length-1)
            			{
            			this.bclick.play();
            			currentrecipes++;
            			this.updaterec();
            			sound8 = false;
            			}
            		}
			
        }
		else
		{
		sound8 = true;
		}
			
	}
	else
	{
		this.rrw.visible = false;
		sound8 = true;
	}
	if(this.redl.input.pointerOver())
	{
		this.rlw.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound9)
            		{
            		
            		 
            		if(currentrecipes>0)
            			{
            			this.bclick.play();
            			currentrecipes--;
            			this.updaterec();
            			sound9 = false;
            			}
            		
            		}
            	
			
        }
		else
		{
		sound9 = true;
		}
	}
	else
	{
		this.rlw.visible = false;
		sound9 = true;
	}
	if(this.invrr.input.pointerOver())
	{
		this.invrw.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound4)
            		{
            		
            		var items = Object.keys(useringredients);
           		 
            		if(currentingredient<items.length-1)
            			{
            			this.bclick.play();
            			currentingredient++;
            			this.updateI();
            			sound4 = false;
            			}
            		}
			
        }
		else
		{
		sound4 = true;
		}
			
	}
	else
	{
		this.invrw.visible = false;
		sound4 = true;
	}
	if(this.invrl.input.pointerOver())
	{
		this.invlw.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound5)
            		{
            		
            		 
            		if(currentingredient>0)
            			{
            			this.bclick.play();
            			currentingredient--;
            			this.updateI();
            			sound5 = false;
            			}
            		
            		}
            	
			
        }
		else
		{
		sound5 = true;
		}
	}
	else
	{
		this.invlw.visible = false;
		sound5 = true;
	}
	if(this.addup.input.pointerOver())
	{
		this.adddown.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound10)
            		{
            		
            		 
            		
            			this.bclick.play();
            			currentamount =0;
            			currenttime = 0;
            			amountmax = 0;
            			sound10 = false;
            			this.checkingredient();
            		
            		}
            	
			
        }
		else
		{
		sound10 = true;
		}
	}
	else
	{
		this.adddown.visible = false;
		sound10 = true;
	}
	if(this.fwo.input.pointerOver())
	{
		this.fwon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound20)
            		{
            		
            		var items = Object.keys(styles);
           		 
            		if(currentstyle<items.length-1)
            			{
            			this.bclick.play();
            			currentstyle++;
            			this.updatestyle();
            			sound20 = false;
            			}
            		}
			
        }
		else
		{
		sound20 = true;
		}
			
	}
	else
	{
		this.fwon.visible = false;
		sound20 = true;
	}
	if(this.bwo.input.pointerOver())
	{
		this.bwon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound21)
            		{
            		
            		 
            		if(currentstyle>0)
            			{
            			this.bclick.play();
            			currentstyle--;
            			this.updatestyle();
            			sound21 = false;
            			}
            		
            		}
            	
			
        }
		else
		{
		sound21 = true;
		}
	}
	else
	{
		this.bwon.visible = false;
		sound21 = true;
	}
	if(this.auoff.input.pointerOver())
	{
		this.auon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound11)
            		{
            			if(currentamount+.9<amountmax)
            				{
            				currentamount++;
            				this.checkingredient();
            				this.bclick.play();
            				this.thought.visible = false;
            				}
            			
            			
            			
            			
            			sound11 = false;
            			
            		}
			
        }
		else
		{
		sound11 = true;
		}
			
	}
	else
	{
		this.auon.visible = false;
		sound11 = true;
	}
	if(this.adoff.input.pointerOver())
	{
		this.adon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound12)
            		{
            		
            		 
            		 
            		if(currentamount>.9)
    				{
	    				currentamount--;
	    				this.checkingredient();
	    				this.bclick.play();
	    				this.thought.visible = false;
    				}
            			 
            			 
            			sound12 = false;
            			
            		
            		}
            	
			
        }
		else
		{
		sound12 = true;
		}
	}
	else
	{
		this.adon.visible = false;
		sound12 = true;
	}
	if(this.aduoff.input.pointerOver())
	{
		this.aduon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound13)
            		{
            		
            		if(currentamount<amountmax-.1)
    				{
	    				currentamount+=.1;
	    				this.checkingredient();
	    				this.bclick.play();
	    				this.thought.visible = false;
    				}
            			
            			
            			sound13 = false;
            			
            		}
			
        }
		else
		{
		sound13 = true;
		}
			
	}
	else
	{
		this.aduon.visible = false;
		sound13 = true;
	}
	if(this.addoff.input.pointerOver())
	{
		this.addon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound14)
            		{
            		
            		if(currentamount>.1)
    				{
	    				currentamount-=.1;
	    				this.checkingredient();
	    				this.bclick.play();
	    				this.thought.visible = false;
    				}
            		 
            		
            			 
            			 
            			sound14 = false;
            			
            		
            		}
            	
			
        }
		else
		{
		sound14 = true;
		}
	}
	else
	{
		this.addon.visible = false;
		sound14 = true;
	}
	if(this.tuoff.input.pointerOver())
	{
		this.tuon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound15)
            		{
            		
            		if(currenttime<120)
    				{
	    				currenttime++;
	    				this.checkingredient();
	    				this.bclick.play();
	    				this.thought.visible = false;
    				}
            		
            			
            			
            			sound15 = false;
            			
            		}
			
        }
		else
		{
		sound15 = true;
		}
			
	}
	else
	{
		this.tuon.visible = false;
		sound15 = true;
	}
	if(this.tdoff.input.pointerOver())
	{
		this.tdon.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound16)
            		{
	            		if(currenttime>0)
	    				{
		    				currenttime--;
		    				this.checkingredient();
		    				this.bclick.play();
		    				this.thought.visible = false;
	    				}
            		 
            		 
            			this.bclick.play();
            			 
            			 
            			sound16 = false;
            			
            		
            		}
            	
			
        }
		else
		{
		sound16 = true;
		}
	}
	else
	{
		this.tdon.visible = false;
		sound16 = true;
	}
	if(this.atroff.input.pointerOver())
	{
		this.atron.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound17)
            		{
            		
            		 
            		 
            			this.bclick.play();
            			 this.addtorecipe();
            			 
            			sound17 = false;
            			
            		
            		}
            	
			
        }
		else
		{
		sound17 = true;
		}
	}
	else
	{
		this.atron.visible = false;
		sound17 = true;
	}
    },
    updateI: function(){
    
    	var items = Object.keys(useringredients);
    	
    	if(currentingredient>-1)
    		{
    		var current = useringredients[items[currentingredient]];
    		var ingredientname = current.name;
    		var ingredientamount = current.amount;
    		var ingredientcategory = current.category;
    		descriptiontext.setText('Current Ingredient\n\nName: ' + ingredientname +'\nCategory: '+ingredientcategory+'\nAmount: ' +ingredientamount.toFixed(2));
    		}
    },
    updaterec: function(){
    	
    	var items = Object.keys(userrecipes);
    	console.log(items);
    	
    	if(currentrecipes>-1)
    		{
    		var current = userrecipes[items[currentrecipes]];
    		var recipename = current.name;
    		recipename = recipename.substr(0,16);
    		headingtext.setText(recipename);
    		}
    },
    updatestyle: function(){
    	var items = Object.keys(styles);
    	if(currentstyle>-1)
		{
		var current = styles[items[currentstyle]];
		var stylename = current.name;
		stylename = stylename.substr(0,40);
		currentstyletext.setText(stylename);
		}
    },
    updaterecipes: function(result){
    	userrecipes = JSON.parse(result);
    	console.log(userrecipes.toString());
    	var items = Object.keys(userrecipes);
    	console.log(items);
    	currentrecipes = items.length-1;
    	if(currentrecipes>-1)
    		{
    		var current = userrecipes[items[currentrecipes]];
    		var recipename = current.name;
    		recipename = recipename.substr(0,16);
    		headingtext.setText(recipename);
    		}
    },
    updateInv: function(result){
    	useringredients = JSON.parse(result);
    	console.log(useringredients.toString());
    	var items = Object.keys(useringredients);
    	currentingredient = items.length-1;
    	if(currentingredient>-1)
    		{
    		currentingredient = 0;
    		var current = useringredients[items[currentingredient]];
    		var ingredientname = current.name;
    		var ingredientamount = current.amount;
    		var ingredientcategory = current.category;
    		descriptiontext.setText('Current Ingredient\n\nName: ' + ingredientname +'\nCategory: '+ingredientcategory+'\nAmount: ' +ingredientamount.toFixed(2));
    		}
    },
    updatestyles: function(result){
    	styles = JSON.parse(result);
    	console.log(styles.toString());
    	var items = Object.keys(styles);
    	console.log(items);
    	currentstyle = items.length-1;
    	if(currentstyle>-1)
    		{
    		currentstyle = 0;
    		var current = styles[items[currentstyle]];
    		var stylename = current.name;
    		stylename = stylename.substr(0,40);
    		currentstyletext.setText(stylename);
    		}
    },
    checkingredient: function(){
    	var items = Object.keys(useringredients);
    	
    		var current = useringredients[items[currentingredient]];
    		var ingredientname = current.name;
    		
    		var ingredientcategory = current.category;
    		if(current.category =="Grain")
    			{
    			this.addgrain();
    			this.showingredientgrain();
    			}
    		else if(current.category=="Hop")
    			{
    			this.addhop();
    			this.showingredienthop();
    			}
    		else if(current.category=="Yeast")
    			{
    			this.addyeast();
    			this.showingredientyeast();
    			}
    		
    },
    addgrain: function(){
    	var items = Object.keys(useringredients);
    	var current = useringredients[items[currentingredient]];
    	var ingredientname = current.name;
    	var ingredientcategory = current.category;
    	if(current.amount>20)
    		{
    		amountmax = 20;
    		}
    	else{
    		amountmax = current.amount;
    		}
    	notetext.setText("Name: "+ current.name +"\nCategory: " + current.category);
    	notetext2.setText("Amount:           " + currentamount.toFixed(2) +" lbs");
    	
    },
    addhop: function(){
    	var items = Object.keys(useringredients);
    	var current = useringredients[items[currentingredient]];
    	var ingredientname = current.name;
    	var ingredientcategory = current.category;
    	if(current.amount>16)
    		{
    		amountmax = 16;
    		}
    	else{
    		amountmax = current.amount;
    		}
    	notetext.setText("Name: "+ current.name +"\nCategory: " + current.category);
    	notetext2.setText("Amount:            " + currentamount.toFixed(1) +" oz");
    	notetext3.setText("Time:   " +currenttime + " Mins");
    },
    addyeast: function(){
    	var items = Object.keys(useringredients);
    	var current = useringredients[items[currentingredient]];
    	notetext.setText("Name: "+ current.name +"\nCategory: " + current.category);
    	notetext2.setText("Amount: 1");
    	currentamount = 1;
    },
    addtorecipe: function(){
    	var items = Object.keys(useringredients);
    	var current = useringredients[items[currentingredient]];
    	if(current.category =="Grain")
		{
    		if(currentamount==0)
    			{
    			this.thought.visible = true;
    			message.setText("Need at least one of each ingredient!");
    			}
    		else
    			{
    			gramount.push(currentamount);
    			grain.push(current.name);
    			current.amount = current.amount-currentamount;
    			this.clearingredient();
    			}
		}
    	else if(current.category=="Hop")
		{
			if(currenttime==0||currentamount==0)
				{
				this.thought.visible = true;
    			message.setText("Need at least one of each ingredient!");
				}
			else
				{
				hop.push(current.name);
				hopamount.push(currentamount);
				hoptime.push(currenttime);
				this.clearingredient();
				}
		}
		else if(current.category=="Yeast")
		{
			this.addyeast();
			this.clearingredient();
		}
    },
    clearingredient: function(){
    	notetext.setText("");
    	notetext2.setText("");
    	notetext3.setText("");
    	this.pad.visible = false;
    	this.auoff.visible = false;
    	this.adoff.visible = false;
    	this.aduoff.visible = false;
    	this.addoff.visible = false;
    	this.tuoff.visible = false;
    	this.tdoff.visible = false;
    	this.atroff.visible = false;
    	
    },
    showingredienthop: function(){
    	this.pad.visible = true;
    	this.auoff.visible = true;
    	this.adoff.visible = true;
    	this.aduoff.visible = true;
    	this.addoff.visible = true;
    	this.tuoff.visible = true;
    	this.tdoff.visible = true;
    	this.atroff.visible = true;
    }, 
    showingredientyeast: function(){
    	this.atroff.visible = true;
    	this.pad.visible = true;
    }, 
    showingredientgrain: function(){
    	this.pad.visible = true;
    	this.auoff.visible = true;
    	this.adoff.visible = true;
    	this.aduoff.visible = true;
    	this.addoff.visible = true;
    	this.atroff.visible = true;
    }
    

    
};
game.state.add('brewing',mainState);
game.state.start('brewing');

