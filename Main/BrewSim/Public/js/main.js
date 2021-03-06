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
var click = true;
var click1 = true;
var click2 = true;
var score = 0;
var balance = 0;
var rank = 0;
var name = "";
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
        name = localStorage.name;
        var pack = packJson("LogReg","getgamestate", name);
        getInfo(pack,this.updateplayerinfo);
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
        balancetext = game.add.text(790,45, 'Balance: $' +balance);
        balancetext.fontSize = 35;
        balancetext.fontWeight = 'bold';
        balancetext.fill = '#FDFEFE';
        balancetext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        ranktext = game.add.text(790,85, 'Brew Rank: ' +rank);
        ranktext.fontSize = 35;
        ranktext.fontWeight = 'bold';
        ranktext.fill = '#FDFEFE';
        ranktext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        scoretext = game.add.text(790,125, 'Score: ' +rank);
        scoretext.fontSize = 35;
        scoretext.fontWeight = 'bold';
        scoretext.fill = '#FDFEFE';
        scoretext.setShadow(4,4, 'rgba(0,0,0,0.5)',0);
        
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
                            game.state.start('brewing');
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
                          
                            game.state.start('market');
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
var game = new Phaser.Game(1200,800);
game.state.add('main',mainState);
//game.state.start('main');