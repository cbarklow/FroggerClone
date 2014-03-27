package com.cbarklow.froggerclone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.sprites.GameSprite;

public class MenuScreen extends Screen {
	
	private SpriteCache _spriteCache;
	private int _spriteCacheIndex;
	
	public MenuScreen(Game game){
		super(game);
	}

	@Override
	public void createScreen() {
		if(elements.size() == 0){
			GameSprite bg = new GameSprite("world", _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.5f);
			GameSprite logo = new GameSprite ("logo", _game, _game.screenWidth * 0.5f,  _game.screenHeight * 0.7f);  
            GameSprite label1 = new GameSprite ("howtoplay", _game, _game.screenWidth * 0.5f,  _game.screenHeight * 0.53f);  
            GameSprite label2 = new GameSprite ("label_instructions", _game, _game.screenWidth * 0.5f,  _game.screenHeight * 0.2f);  
            GameSprite label3 = new GameSprite ("taptobegin", _game, _game.screenWidth * 0.5f,  _game.screenHeight * 0.02f);  
            GameSprite control = new GameSprite ("control", _game, _game.screenWidth * 0.5f,  _game.screenHeight * 0.4f);
            
            /* 
            //OPTION 1: With SpriteBatch 
            elements.add(logo); 
            elements.add(label1); 
            elements.add(label2); 
            elements.add(label3); 
            elements.add(control); 
            */  
              
            //OPTION 2: With SpriteCache              
            _spriteCache = new SpriteCache();  
            _spriteCache.beginCache(); 
            _spriteCache.add(bg.skin, bg.x, bg.y);
            _spriteCache.add(logo.skin, logo.x, logo.y);  
            _spriteCache.add(label1.skin, label1.x, label1.y);  
            _spriteCache.add(label2.skin, label2.x, label2.y);  
            _spriteCache.add(label3.skin, label3.x, label3.y);  
            _spriteCache.add(control.skin, control.x, control.y);  
            _spriteCacheIndex = _spriteCache.endCache();  
		}

	}

	@Override
	public void update(float dt) {
		if(Gdx.input.justTouched()){
			Gdx.app.log("Touch Event: ", "Screen Touched");
			_game.setScreen("GameScreen");
		} else{
			GL20 gl = Gdx.gl20;  
            gl.glClearColor(0.4f, 0.4f, 0.4f, 1);  
            gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
              
            _game.camera.update();  
              
            /* 
            //OPTION 1: With SpriteBatch 
            _game.spriteBatch.setProjectionMatrix(_game.camera.combined); 
            _game.spriteBatch.enableBlending(); 
            _game.spriteBatch.begin(); 
             
            int len = elements.size(); 
            GameSprite element; 
            for (int i = 0; i < len; i++) { 
                element = elements.get(i); 
                _game.spriteBatch.draw(element.skin, element.x, element.y); 
            } 
            _game.spriteBatch.end(); 
            */  
              
            //OPTION 2: With SpriteCache  
            gl.glEnable(GL20.GL_BLEND);  
            gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);  
            _spriteCache.setProjectionMatrix(_game.camera.combined);  
            _spriteCache.begin();  
            _spriteCache.draw(_spriteCacheIndex);  
            _spriteCache.end();  
		}

	}

}
