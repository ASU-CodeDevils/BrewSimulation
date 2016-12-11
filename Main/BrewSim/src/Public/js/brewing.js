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
var sound1 = true;
var sound2 = true;
var sound4 = true;
var sound5 = true;
var sound7 = true;
var userrecipes;
var useringredients;
var currentrecipe = 0;
var currentingredient = 0;
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
    },
    create: function(){
        game.stage.backgroundColor = '#F5F1DE';
        game.physics.startSystem(Phaser.Physics.ARCADE);
        game.add.tileSprite(0,0,1200,800,'brewback');
        var pack = packJson("LogReg","getUserRecipes", name);
        getInfo(pack,this.updaterecipes);
        var pack = packJson("LogReg","getUserInv", name);
        getInfo(pack,this.updateInv);
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
        headingtext = game.add.text(438,122, '12345678910111213141516');
        headingtext.fontSize = 24;
        headingtext.fontWeight = 'bold';
        headingtext.fill = '#FDFEFE';
        headingtext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        headingtext.anchor.set(0.5);
        
       
        this.rrw = game.add.sprite(618,98,'rrw');
        this.rrw.visible = false;
        this.rlw = game.add.sprite(209,98,'rlw');
        this.rlw.visible = false;
        this.back = game.add.sprite(20,40,'leftarrow');
        this.back.inputEnabled = true;
        this.back.input.userHandCursor = true;
        this.tagback = game.add.sprite(56, 206,'tagback');
        this.backdown = game.add.sprite(20,40, 'leftdown');
        this.backdown.visible = false;
    
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
			
			
            	if(sound4)
            		{
            		
            		var items = Object.keys(userrecipes);
           		 
            		if(currentrecipe<items.length-1)
            			{
            			this.bclick.play();
            			currentrecipe++;
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
		this.rrw.visible = false;
		sound4 = true;
	}
	if(this.redl.input.pointerOver())
	{
		this.rlw.visible = true;
		if(game.input.activePointer.isDown)
        {
			
			
            	if(sound5)
            		{
            		
            		 
            		if(currentrecipe>0)
            			{
            			this.bclick.play();
            			currentrecipe++;
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
		this.rlw.visible = false;
		sound5 = true;
	}
	
    },
    updaterecipes: function(result){
    	userrecipes = JSON.parse(result);
    	console.log(userrecipes.toString());
    	var items = Object.keys(userrecipes);
    	console.log(items);
    	currentrecipe = items.length-1;
    	if(currentrecipe>-1)
    		{
    		var current = userrecipes[items[currentrecipe]];
    		var recipename = current.name;
    		recipename = recipename.substr(0,16);
    		headingtext.setText(recipename);
    		}
    },
    updateInv: function(result){
    	useringredients = JSON.parse(result);
    	console.log(useringredients.toString());
    },
    
};
game.state.add('brewing',mainState);
game.state.start('brewing');

