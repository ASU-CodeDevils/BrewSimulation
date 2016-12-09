var click = true;
var click1 = true;
var click2 = true;
var mainState={
    preload:function(){
        game.load.image('back','graphics/Garage.png');
        game.load.image('money','graphics/money.png');
        game.load.image('moneyhover','graphics/moneyhover.png');
        game.load.image('beerclick','graphics/beer.png');
        game.load.image('beerclicked','graphics/beerclick.png');
        game.load.image('shop','graphics/shop.png');
        game.load.image('shopclicked','graphics/shopclicked.png');
        game.load.audio('mouseover','sounds/select.wav');
        game.load.audio('soundbeer','sounds/bubble.wav');
        game.load.audio('soundreg', 'sounds/register.wav');
        game.load.audio('shopsound', 'sounds/tone.wav');
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
        this.beerclick = game.add.sprite(450, 320,'beerclick');
        this.beerclick.inputEnabled = true;
        this.beerclick.input.useHandCursor = true;
        this.beerclicked=game.add.sprite(438, 280, 'beerclicked');
        this.beerclicked.visible =false;
        this.shop = game.add.sprite(650,335,'shop');
        this.shop.inputEnabled = true;
        this.shop.input.useHandCursor =true;
        this.shopclicked = game.add.sprite(650,335,'shopclicked');
        this.shopclicked.visible = true;
        this.hover= game.add.audio('mouseover');
        this.soundbeer = game.add.audio('soundbeer');
        this.soundreg = game.add.audio('soundreg');
        this.soundshop = game.add.audio('shopsound');
         
    },
    update: function(){
             
            if(this.money.input.pointerOver())
                {
                    if(game.input.activePointer.isDown)
                        {
                            this.soundreg.play();
                        }
                    this.moneyhover.visible = true;
                    if(click)
                        {
                            this.hover.play();
                            click = false
                        }
                  
                }
            else
                {
                  this.moneyhover.visible = false; 
                  click = true;
                 
                  
                }
            if(this.beerclick.input.pointerOver())
                {
                    if(game.input.activePointer.isDown)
                        {
                            this.soundbeer.play();
                        }
                    this.beerclicked.visible = true;
                    if(click1)
                        {
                            this.hover.play();
                            click1 = false
                        }
                    
                }
            else
                {
                    this.beerclicked.visible = false;
                   click1 = true;
                }
            if(this.shop.input.pointerOver())
                {
                    this.shopclicked.visible = true;
                     if(game.input.activePointer.isDown)
                        {
                            this.soundshop.play();
                        } 
                    if(click2)
                        {
                            this.hover.play();
                            click2 = false
                        }
                }
            else
                {
                    this.shopclicked.visible = false;
                   click2 = true;
                }
        
    },
    moneyClick: function(){
        console.log('oops');
    },
    beerClick: function(){
        console.log('oops');
    },
    up: function(){
        console.log('button up',arguments);
    },
    over: function(){
        console.log('button over');
    },
    out: function(){
        console.log('button out');
    },
    
};
var game = new Phaser.Game(1200,800);
game.state.add('main',mainState);
game.state.start('main');