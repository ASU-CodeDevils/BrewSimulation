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
var ingredients=[];
var currentingredient =0;
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
        var pack = packJson("LogReg", "getIngredients",name);
        getInfo(pack,getinvitems);
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
				}
			
			}
    		
    	}
    	else
    		{
    		this.buydown.visible = false;
    		sound6 = true;
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
                	sound4 = false;
                	}
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
                	sound5 = false;
                	}
            }
		}
    	else
		{
    		this.rlw.visible = false;
    		sound5 = true;
		}

    },
    loadinventory: function(){
    	var current = (ingredients[currentingredient].Ingredient);
    	console.log(current);
    	
    	console.log(current.graphic);
   	 	this.item = game.add.sprite(200,520,current.graphic);
   	 	this.item.anchor.set(0.5);
   	 	descriptiontext.setText(current.name + '\nCategory: '+current.category+'\nPrice: ' + current.price +'\nAvailable: ' + current.amount +'\n\nDecription: '+current.description);
   	 	this.buyup.visible = true;
    },
    loadrecipe: function(){
    	this.item.visible = false;
    	descriptiontext.setText('');
    },
    loadequipment: function(){
    	descriptiontext.setText('');
    	this.item.visible = false;
    }
    
   
};


game.state.add('market',mainState);
game.state.start('market');


getinvitems = function(result){
	ingredients.push(JSON.parse(result));
}