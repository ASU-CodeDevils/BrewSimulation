var score = 0;
var balance = 0;
var rank = 0;
var name = "";
var mainState= {
    preload: function(){
        game.load.image('back','graphics/Storeback.png');
        game.load.image('bamboo','graphics/bamboo.png');
        game.load.image('gin', 'graphics/gin.png');
        game.load.image('gout', 'graphics/gout.png');
        game.load.audio('bclick','sounds/bclick.wav');
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
        
    },
    update: function(){
    	if(this.gout1.input.pointerOver())
    		{
    		this.gin1.visible = true;
    		}
    	else
    		{
    		this.gin1.visible = false;
    		}
    	if(this.gout2.input.pointerOver())
		{
    		this.gin2.visible = true;
		}
    	else
		{
    		this.gin2.visible = false;
		}
    	if(this.gout3.input.pointerOver())
		{
    		this.gin3.visible = true;
		}
    	else
		{
    		this.gin3.visible = false;
		}
    },
};

game.state.add('market',mainState);
game.state.start('market');