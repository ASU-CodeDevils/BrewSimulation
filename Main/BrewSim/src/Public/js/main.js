var mainState={
    preload:function(){
        game.load.image('back','graphics/Garage.png');
        game.load.image('money','graphics/money.png');
        game.load.image('moneyhover','graphics/moneyhover.png');
    },
    create: function(){
        game.stage.backgroundColor = '#F5F1DE';
        game.physics.startSystem(Phaser.Physics.ARCADE);
        game.add.tileSprite(0,0,1200,800, 'back');
        
        
        this.money = game.add.sprite(260,383,'money');
        this.money.inputEnabled = true;
        this.money.input.useHandCursor = true;
        this.moneyhover = game.add.sprite(260,389,'moneyhover');
        this.moneyhover.visible = false;
    },
    update: function(){
            if(this.money.input.pointerOver())
                {
                   this.moneyhover.visible = true;
                   
                }
            else
                {
                  this.moneyhover.visible = false; 
                  
                }
        
    },
    moneyClick: function(){
    
    },
    
};
var game = new Phaser.Game(1200,800);
game.state.add('main',mainState);
game.state.start('main');