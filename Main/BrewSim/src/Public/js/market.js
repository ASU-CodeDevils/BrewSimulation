var score = 0;
var balance = 0;
var rank = 0;
var name = "";
var sound1 = true;
var sound2 = true;
var sound3 = true;
var sound4 = true;
var sound5 = true;
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
        this.redl = game.add.sprite(80,260,'redl');
        this.redl.inputEnabled = true;
        this.redl.input.useHandCursor = true;
        this.redr = game.add.sprite(385,260,'redr');
        this.redr.inputEnabled = true;
        this.redr.input.useHandCursor = true;
        this.reds = game.add.sprite(165,265,'reds');
        this.reds.inputEnabled = true;
        this.reds.input.useHandCursor = true;
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
        getInfo(pack,function(){
        	var current = ingredients[currentingredient];
       	 	this.item = game.add.sprite(277	,550,'barley');
       	 	this.item.anchor.set(0.5);
        });
        
        
    },
    loadingred: function(result){
    	result = JSON.parse(result);
    	for(var key in result)
    		{
    			ingedients.push(result[key]);
    		}
    },
    update: function(){
    	 
    	if(this.gout1.input.pointerOver())
    		{
    		this.gin1.visible = true;
    		if(game.input.activePointer.isDown)
            {
                if(sound1)
                	{
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
    	var current = ingredients[currentingredient];
    	 this.tem = game.add.sprite(165,265,current.graphic);
    	 this.item.anchor.set(0.5);
   }
};


game.state.add('market',mainState);
//game.state.start('market');
