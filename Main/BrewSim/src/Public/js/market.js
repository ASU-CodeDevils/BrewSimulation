var score = 0;
var balance = 0;
var rank = 0;
var name = "";
var back = true;
var sound1 = true;
var sound2 = true;
var sound3 = true;
var sound4 = true;
var sound5 = true;
var sound6 = true;
var sound7 = true;
var upsound = true;
var downsound = true;
var purch = true;
var quantity = 0;
var price = 0;
var click = true;
var iflast = true;
var inload = true;
var recipeload = false;
var equipmentload = false;
var ingredients;
var recipes;
var equipment;
var currentitem =0;
var currentrecipe = 0;
var currentequip = 0;
var hasnotloaded = true;

var mainState= {
    preload: function(){
        game.load.image('back','graphics/Storeback.png');
        game.load.image('bamboo','graphics/bamboo.png');
        game.load.image('gin', 'graphics/gin.png');
        game.load.image('gout', 'graphics/gout.png');
        game.load.audio('bclick','sounds/bclick.wav');
        game.load.image('redl','graphics/redl.png');
        game.load.image('redr','graphics/redr.png');
        game.load.image('reds', 'graphics/reds.png');
        game.load.image('rrw', 'graphics/rrw.png');
        game.load.image('rlw', 'graphics/rlw.png');
        game.load.image('barley','graphics/Barley.png');
        game.load.image('leftarrow','graphics/leftarrow.png');
        game.load.image('buydown', 'graphics/buydown.png');
        game.load.image('buyup', 'graphics/buyup.png');
        game.load.image('leftdown', 'graphics/leftarrowdown.png');
        game.load.image('yeast','graphics/yeast.png');
        game.load.image('grain','graphics/grain.png');
        game.load.image('hop','graphics/hops.png');
        game.load.image('buyback','graphics/buyback.png');
        game.load.image('upoff','graphics/topoff.png');
        game.load.image('upon','graphics/upon.png');
        game.load.image('downoff','graphics/downoff.png');
        game.load.image('downon','graphics/downon.png');
        game.load.image('purchu', 'graphics/purchu.png');
        game.load.image('purchd','graphics/purchd.png');
        game.load.image('recipe','graphics/recipe.png');
    },
    create: function(){
        game.stage.backgroundColor = '#F5F1DE';
        game.physics.startSystem(Phaser.Physics.ARCADE);
        game.add.tileSprite(0,0,1200,800,'back');
        this.bamboo = game.add.sprite(724,150,'bamboo');
        name = localStorage.name;
        balancetext = game.add.text(750,160, 'Balance: $' +balance);
        balancetext.fontSize = 35;
        balancetext.fontWeight = 'bold';
        balancetext.fill = '#FDFEFE';
        balancetext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        ranktext = game.add.text(750,200, 'Brew Rank: ' +rank);
        ranktext.fontSize = 35;
        ranktext.fontWeight = 'bold';
        ranktext.fill = '#FDFEFE';
        ranktext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        scoretext = game.add.text(750,240, 'Score: ' +rank);
        scoretext.fontSize = 35;
        scoretext.fontWeight = 'bold';
        scoretext.fill = '#FDFEFE';
        scoretext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        this.gout1 = game.add.sprite(80,212,'gout');
        this.gout1.inputEnabled = true;
        this.gout1.input.useHandCursor = true;
        this.gout2 = game.add.sprite(210,212,'gout');
        this.gout2.inputEnabled = true;
        this.gout2.input.useHandCursor = true;
        this.gout3 = game.add.sprite(340,212,'gout');
        this.gout3.inputEnabled = true;
        this.gout3.input.useHandCursor = true;
        this.gin1 = game.add.sprite(80,212,'gin');
        this.gin1.visible = false;
        this.gin2 = game.add.sprite(210,212,'gin');
        this.gin2.visible = false;
        this.gin3 = game.add.sprite(340,212,'gin');
        this.gin2.visible = false;
        ingredienttext = game.add.text(88,220, 'Ingredients');
        ingredienttext.fontSize = 15;
        ingredienttext.fontWeight = 'bold';
        ingredienttext.fill = '#FDFEFE';
        ingredienttext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        recipetext = game.add.text(228,220, 'Recipes');
        recipetext.fontSize = 15;
        recipetext.fontWeight = 'bold';
        recipetext.fill = '#FDFEFE';
        recipetext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        equiptext = game.add.text(350,220, 'Equipment');
        equiptext.fontSize = 15;
        equiptext.fontWeight = 'bold';
        equiptext.fill = '#FDFEFE';
        equiptext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        
        
        
        this.buyback = game.add.sprite(674,326,'buyback');
        this.buyback.visible = true;
        
        this.bclick = game.add.audio('bclick');
        this.back = game.add.sprite(20,40,'leftarrow');
        this.back.inputEnabled = true;
        this.back.input.userHandCursor = true;
        this.backdown = game.add.sprite(20,40, 'leftdown');
        this.backdown.visible = true;
        this.redl = game.add.sprite(80,260,'redl');
        this.redl.inputEnabled = true;
        this.redl.input.useHandCursor = true;
        this.redr = game.add.sprite(385,260,'redr');
        this.redr.inputEnabled = true;
        this.redr.input.useHandCursor = true;
        this.reds = game.add.sprite(165,265,'reds');
       
        this.rrw = game.add.sprite(385,260,'rrw');
        this.rrw.visible = false;
        this.rlw = game.add.sprite(80,260,'rlw');
        this.rlw.visible = false;
        headingtext = game.add.text(255,285, 'Ingredients');
        headingtext.fontSize = 24;
        headingtext.fontWeight = 'bold';
        headingtext.fill = '#FDFEFE';
        headingtext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        headingtext.anchor.set(0.5);
        var pack = packJson("LogReg", "getIngredients");
        getInfo(pack,getinvitems);
        var pack = packJson("LogReg","getRecipes");
        getInfo(pack,getrecipes);
        descriptiontext = game.add.text(310,370, '');
        descriptiontext.fontSize = 18;
        descriptiontext.fill = '#FDFEFE';
        descriptiontext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        descriptiontext.wordWrap= true;
        descriptiontext.wordWrapWidth = 150;
        //descriptiontext.anchor.set(0.5);
        this.buyup = game.add.sprite(195,670,'buyup');
        this.buyup.inputEnabled = true;
        this.buyup.input.useHandCursor = true;
        this.buyup.visible = false;
        this.buydown = game.add.sprite(195,670, 'buydown');
        this.buydown.visible = false;
        this.item = game.add.sprite(190,520,'wheat');
   	 	this.item.anchor.set(0.5);
   	 	this.item.visible = false;
        this.upoff = game.add.sprite(1113,410,'upoff');
        this.upoff.inputEnabled = true;
        this.upoff.input.useHandCursor = true;
        this.downoff = game.add.sprite(1113,460,'downoff');
        this.downoff.inputEnabled = true;
        this.downoff.input.useHandCursor = true;
        this.upon = game.add.sprite(1113,410,'upon');
        this.upon.visible = false;
        
        this.downon = game.add.sprite(1113,460,'downon');
        this.downon.visible = false;
        this.recipe = game.add.sprite(190,520,'recipe');
        this.recipe.anchor.set(0.5);
        this.recipe.visible = false;
        
        buytext = game.add.text(703,416, '');
        buytext.fontSize = 18;
        buytext.fill = '#FDFEFE';
        buytext.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        buytext.wordWrap= true;
        buytext.wordWrapWidth = 200;
        buytext1 = game.add.text(934,434, '');
        buytext1.fontSize = 18;
        buytext1.fill = '#FDFEFE';
        buytext1.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        buytext1.wordWrap= true;
        buytext1.wordWrapWidth = 200;
        buytext2 = game.add.text(811,560, '');
        buytext2.fontSize = 18;
        buytext2.fill = '#FF0000';
        buytext2.setShadow(2,2, 'rgba(0,0,0,0.5)',0);
        buytext2.wordWrap= true;
        buytext2.wordWrapWidth = 400;
        this.upoff.visible = false;
    	this.downoff.visible = false;
    	this.buyback.visible = false;
    	this.purchu = game.add.sprite(867,593,'purchu');
        this.purchu.inputEnabled = true;
        this.purchu.input.useHandCursor = true;
        this.purchu.visible = false;
        this.purchd = game.add.sprite(867,593,'purchd');
        this.purchd.visible = false;
        var pack = packJson("LogReg","getgamestate", name);
        getInfo(pack,this.updateplayerinfo);
       
        
    },
    loadingred: function(result){
    	result = JSON.parse(result);
    	for(var key in result)
    		{
    			ingedients.push(result[key]);
    		}
    },
    update: function(){
    	console.log(game.input.mousePointer.x);
    	console.log(game.input.mousePointer.y);
    	if(this.buyup.input.pointerOver()){
    		this.buydown.visible = true;
    		if(game.input.activePointer.isDown)
			{
			if(sound6)
				{
				this.bclick.play();
				sound6=false;
				console.log("load buy menu");
				if(inload){
					this.clickbuy();
				}
				else if(recipeload){
					this.clickrecequip();
				}
				else if(equipmentload){
					
				}
				}
			
			}
    		
    	}
    	else
    		{
    		this.buydown.visible = false;
    		sound6 = true;
    		}
    	if(this.purchu.input.pointerOver()){
    		this.purchd.visible = true;
    		if(game.input.activePointer.isDown)
			{
			if(purch)
				{
				this.bclick.play();
				purch=false;
				console.log("load buy menu");
				this.purch();
				}
			
			}
    		
    	}
    	else
    		{
    		this.purchd.visible = false;
    		purch = true;
    		}
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
    		else
			{
    			sound7 =true;
    			
			}
    		}
    	else
    		{
    		this.backdown.visible = false;
    		
    		}
    	if(this.gout1.input.pointerOver())
    		{
    		this.gin1.visible = true;
    		if(game.input.activePointer.isDown)
            {
                if(sound1)
                	{
                	this.loadinventory();
                	this.bclick.play();
                	sound1 = false;
                	headingtext.setText("Ingredients");
                	recipeload = false;
                	equipmentload = false;
                	inload = true;
                	this.clearbuy();
                  	}
            }
    		
    		}
    	else
    		{
    		this.gin1.visible = false;
    		sound1 = true;
    		}
    	if(this.gout2.input.pointerOver())
		{
    		this.gin2.visible = true;
    		if(game.input.activePointer.isDown)
            {
                if(sound2)
                	{
                	this.bclick.play();
                	this.loadrecipe();
                	sound2 = false;
                	headingtext.setText("Recipes");
                	recipeload = true;
                	equipmentload = false;
                	inload = false;
                	this.clearbuy();
                	}
            }
		}
    	else
		{
    		this.gin2.visible = false;
    		sound2 = true;
		}
    	if(this.gout3.input.pointerOver())
		{
    		this.gin3.visible = true;
    		if(game.input.activePointer.isDown)
            {
                if(sound3)
                	{
                	this.bclick.play();
                	sound3 = false;
                	this.loadequipment();
                	headingtext.setText("Equipment");
                	recipeload = false;
                	equipmentload = true;
                	inload = false;
                	this.clearbuy();
                	}
            }
		}
    	else
		{
    		this.gin3.visible = false;
    		sound3 = true;
		}
    	if(this.redr.input.pointerOver())
		{
    		this.rrw.visible = true;
    		if(game.input.activePointer.isDown)
            {
    			
    			
                	if(sound4)
                		{
                		
                		this.bclick.play();
                		this.clearbuy();
                		if(inload){
                			
                			if(iflast)
                				{
                					currentitem++;
                				}
                			else {
                				currentitem +=1;
                				iflast=true;
                			}
                			this.loadinventory();
                		}
                		else if(recipeload){
                			this.loadrecipe();
                		}
                		else if(equipmentload){
                			this.loadequipment();
                		}
                		sound4 = false;
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
                		this.bclick.play();
                		this.clearbuy();
                		if(inload){
                			
                			
                			if(!iflast)
            				{
            					currentitem--;
            				}
                			else {
                				currentitem -=1;
                				iflast = false;
            				}
                			this.loadinventory();
                		}
                		else if(recipeload){
                			this.loadrecipe();
                		}
                		else if(equipmentload){
                			this.loadequipment();
                		}
                		sound5 = false;
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
    	if(this.upoff.input.pointerOver())
		{
    		this.upon.visible = true;
    		if(game.input.activePointer.isDown)
            {
    			
    			
                	if(upsound)
                		{
                		
                		this.bclick.play();
                		this.updatebuyup();
                		
                		upsound = false;
                		}
                	
    			
            }
    		else
    		{
    		upsound = true;
    		}
    			
		}
    	else
		{
    		this.upon.visible = false;
    		upsound = true;
		}
    	if(this.downoff.input.pointerOver())
		{
    		this.downon.visible = true;
    		if(game.input.activePointer.isDown)
            {
    			
    			
                	if(downsound)
                		{
                		this.updatebuydown();
                		this.bclick.play();
                		
                		
                		downsound = false;
                		}
                	
    			
            }
    		else
    		{
    		downsound = true;
    		}
    			
		}
    	else
		{
    		this.downon.visible = false;
    		downsound = true;
		}

    },
    loadinventory: function(){
    	var items = Object.keys(ingredients);
    	console.log(items);
    	console.log(currentitem);
    	var inload = true;
    	var recipeload = false;
    	var equipmentload = false;
    	if(currentitem>=items.length)
    		currentitem = 0;
    	else if(currentitem<0)
    		currentitem = items.length -1;
    	var current = ingredients[items[currentitem]];
    	this.recipe.visible = false;
    	console.log(currentitem);
    	if(!click)
    		{
    		this.item.destroy();
    		}
    	click = false;
   	 	this.item = game.add.sprite(190,520,current.graphic);
   	 	this.item.anchor.set(0.5);
   	 	descriptiontext.setText(current.name + '\nCategory: '+current.category+'\nPrice: ' + current.price.toFixed(2) +'\nAvailable: ' + current.amount +'\n\nDecription: '+current.description);
   	 	this.buyup.visible = true;
    },
    loadrecipe: function(){
    	var inload = false;
    	var recipeload = true;
    	var equipmentload = false;
    	this.item.visible = false;
    	var items = Object.keys(recipes);
    	if(currentrecipe>=items.length)
    		currentrecipe = 0;
    	else if(currentrecipe<0)
    		currentrecipe = items.length -1;
    	var current = recipes[items[currentrecipe]];
    	this.recipe.visible = true;
    	
    	descriptiontext.setText(current.name +'\n\nPrice: ' + current.price.toFixed(2));
   	 	this.buyup.visible = true;
    },
    loadequipment: function(){
    	var inload = false;
    	var recipeload = false;
    	var equipmentload = true;
    	descriptiontext.setText('');
    	this.item.visible = false;
    	this.recipe.visible = false;
    },
    clickbuy: function(){
    	var items = Object.keys(ingredients);
    	
    	var current = ingredients[items[currentitem]];
    	this.upoff.visible = true;
    	this.downoff.visible = true;
    	this.buyback.visible = true;
    	this.purchu.visible = true;
    	buytext.setText(current.name+'\nAmount Available:'+current.amount.toFixed(2) +'\nPrice: '+current.price);
    	buytext1.setText('Quantity to buy: '+ quantity + '\nTotal Price: ' + price.toFixed(2));
    },
    clickrecequip: function(){
    	var items = Object.keys(recipes);
    	
    	var current = recipes[items[currentrecipe]];
    	
    	this.buyback.visible = true;
    	this.purchu.visible = true;
    	price = current.price;
    	quantity = 1;
    	buytext.setText(current.name+'\nPrice: '+current.price);
    	
    },
    updatebuyup: function(){
    	var items = Object.keys(ingredients);
    	var current = ingredients[items[currentrecipe]];
    	
    	if(quantity<current.amount){
    	quantity++;
    	price = current.price*quantity;
    	buytext.setText(current.name+'\nAmount Available:'+current.amount.toFixed(2) +'\nPrice: '+current.price);
    	buytext1.setText('Quantity to buy: '+ quantity + '\nTotal Price: ' + price.toFixed(2));
    	}
    },
    updatebuydown: function(){
    	var items = Object.keys(ingredients);
    	var current = ingredients[items[currentitem]];
    	
    	if(quantity>0){
    	quantity--;
    	price = current.price*quantity;
    	buytext.setText(current.name+'\nAmount Available:'+current.amount.toFixed(2) +'\nPrice: '+current.price);
    	buytext1.setText('Quantity to buy: '+ quantity + '\nTotal Price: ' + price.toFixed(2));
    	}
    },
    clearbuy: function(){
    	this.upoff.visible = false;
    	this.downoff.visible = false;
    	this.buyback.visible = false;
    	this.purchu.visible = false;
    	buytext.setText("");
    	buytext1.setText("");
    	buytext2.setText("");
    	quantity = 0;
    	price = 0;
    	var pack = packJson("LogReg","getgamestate", name);
        getInfo(pack,this.updateplayerinfo);
    },
    purch: function(){
    	if(inload){
	    	var items = Object.keys(ingredients);
	    	var current = ingredients[items[currentitem]];
	    	if(price>balance)
	    		{
	    			buytext2.setText("You don't have that much money!");
	    			
	    		}
	    	else
	    		{
	    		var pack = packJson("LogReg","purchase",name,current.name, quantity.toFixed(2),price.toFixed(2));
	    		
	            getInfo(pack,null);
	            this.clearbuy();
	    		}
    	}
    	else if(recipeload){
    		var items = Object.keys(recipes);
    		var current = recipes[items[currentrecipe]];
    		if(price>balance)
    		{
    			buytext2.setText("You don't have that much money!");
    			
    		}
    		else
    		{
    		var pack = packJson("LogReg","purchase",name,current.name, quantity.toFixed(2),price.toFixed(2));
    		
            getInfo(pack,null);
            this.clearbuy();
    		}
    	}
    },
    updateplayerinfo: function(result){
    	console.log(result);
    	result = JSON.parse(result);
    	balance = result.Balance;
    	score = result.BrewScore;
    	rank = result.BrewRank;
    	balancetext.setText("Balance: $" + balance);
    	ranktext.setText("Brew Rank: " + rank);
    	scoretext.setText("Score: " + score);
    	console.log(balance, score, rank);
    	
    }
    
   
};


game.state.add('market',mainState);
game.state.start('market');

getrecipes = function(result){
	recipes = JSON.parse(result);
}
getinvitems = function(result){
	ingredients=JSON.parse(result);
}