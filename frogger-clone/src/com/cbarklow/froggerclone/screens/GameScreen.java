package com.cbarklow.froggerclone.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.Sounds;
import com.cbarklow.froggerclone.elements.BonusCharacter;
import com.cbarklow.froggerclone.elements.Controls;
import com.cbarklow.froggerclone.elements.FinalTier;
import com.cbarklow.froggerclone.elements.Level;
import com.cbarklow.froggerclone.elements.Lives;
import com.cbarklow.froggerclone.elements.Player;
import com.cbarklow.froggerclone.elements.Score;
import com.cbarklow.froggerclone.elements.Tier;
import com.cbarklow.froggerclone.elements.TimeBar;
import com.cbarklow.froggerclone.elements.TimeMsg;
import com.cbarklow.froggerclone.sprites.GameSprite;

public class GameScreen extends Screen {
	
	private Player _player;  
    private BonusCharacter _bonusFrog;  
    private Controls _controls;  
    private TimeBar _timeBar;  
    private GameSprite _gameOverMsg;  
    private GameSprite _newLevelMsg;  
    private TimeMsg _levelTimeMsg;  
    private Score _score;  
    private Level _level;  
    private Lives _lives;  
    private Vector3 _touchPoint;  
    private Rectangle _controlBounds;  
      
    private List<Tier> _tiers;  
    
    public GameScreen(Game game){
    	super(game);
    	_tiers = new ArrayList<Tier>();
    	_touchPoint = new Vector3();
    }

	@Override
	public void createScreen() {
		if(elements.size() == 0){
			//add background
			elements.add(new GameSprite("world", _game, _game.screenWidth * 0.5f, _game.screenHeight * 0.5f));
			
			for(int i = 0; i < 12; i++){
				_tiers.add(new Tier(_game, i));
			}
			
			_tiers.add(new FinalTier(_game, 12));
			
			elements.add(new GameSprite("finaltier", _game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.13f));
			
			_player = new Player (_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.89f);  
            _bonusFrog = new BonusCharacter (_game, -100, -100, _player);  
            _bonusFrog.floor = _tiers.get(8).getElement(0);  
              
            elements.add(new GameSprite ("timelabel2", _game, _game.screenWidth * 0.1f, _game.screenHeight * 0.04f));  
              
            _timeBar = new TimeBar (_game, _game.screenWidth * 0.18f, _game.screenHeight * 0.03f);  
              
            _score = new Score (_game, _game.screenWidth * 0.2f, _game.screenHeight - _game.screenHeight * 0.05f, "red");  
            _level = new Level (_game, _game.screenWidth * 0.04f, _game.screenHeight - _game.screenHeight * 0.05f, "white");  
            _lives = new Lives (_game, _game.screenWidth * 0.68f, _game.screenHeight - _game.screenHeight * 0.06f);  
          
            _controls = new Controls (_game, _game.screenWidth * 0.82f, _game.screenHeight - _game.screenHeight * 0.88f);  
            _controlBounds = _controls.bounds();  
              
            _gameOverMsg = new GameSprite("gameover", _game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f);  
            _gameOverMsg.visible = false;  
            elements.add(_gameOverMsg);  
              
            _newLevelMsg = new GameSprite("newlevel", _game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f);  
            _newLevelMsg.visible = false;  
            elements.add(_newLevelMsg);  
              
            _levelTimeMsg = new TimeMsg(_game, _game.screenWidth * 0.5f, _game.screenHeight - _game.screenHeight * 0.53f);  
            _levelTimeMsg.visible = false;                                     
          
        } else {  
              
            _timeBar.reset();  
            _player.reset();  
            _bonusFrog.reset();  
            _score.reset();  
            _level.reset();  
            _game.gameData.reset();  
            _lives.show();  
              
            for (int i = 0; i < _tiers.size(); i++) {  
                _tiers.get(i).reset();  
            }  
        }  
          
        _game.gameData.gameMode = Game.GAME_STATE_PLAY;		
	}

	@Override
	public void update(float dt) {
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //check for input  
        if (Gdx.input.justTouched()) {         	
            if (_gameOverMsg.visible) {  
                _gameOverMsg.visible = false;  
                _game.setScreen("MenuScreen");  
            } else {  
                  
                if (_game.gameData.gameMode == Game.GAME_STATE_PAUSE) return;  
                  
                //test for touch on Controls  
                if (!_player.moving && _player.visible && _game.gameData.gameMode == Game.GAME_STATE_PLAY) {  
                    
                	_game.camera.unproject(_touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));  
                   
                    if (_controlBounds.contains(_touchPoint.x, _touchPoint.y)) {  
                        switch (_controls.getDirection(_touchPoint)) {  
                            case Player.MOVE_TOP:  
                                _player.movePlayerUp();  
                                break;  
                            case Player.MOVE_DOWN:  
                                _player.movePlayerDown();  
                                break;  
                            case Player.MOVE_LEFT:  
                                _player.movePlayerLeft();  
                                break;  
                            case Player.MOVE_RIGHT:  
                                _player.movePlayerRight();  
                                break;  
                        }  
                    }  
                }  
            }  
        } else if(Gdx.input.isKeyPressed(Keys.UP)){
        	_player.movePlayerUp();
        } else if(Gdx.input.isKeyPressed(Keys.DOWN)){
        	_player.movePlayerDown();
        } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	_player.movePlayerLeft();
        } else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
        	_player.movePlayerRight();
        }
          
          
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //update elements!  
        _player.update(dt);  
        _player.place();  
        _bonusFrog.update(dt);  
        _bonusFrog.place();  
          
        int i;  
        int len = _tiers.size();  
        //update all tiers  
        for (i = 0; i < len; i++) {  
         _tiers.get(i).update(dt);  
        }  
          
        //check for collisions!  
        if (_player.active) {  
            //check collision of hero and tier sprites  
            if (_tiers.get(_player.tierIndex).checkCollision(_player)) {  
                //if tiers with monster, and colliding with monster  
                if (_player.tierIndex < 6) {  
                    Sounds.play(Sounds.hit);  
                    //if not colliding with anything in the water tiers, fall into bottomless pit  
                } else {  
                    Sounds.play(Sounds.splash);                   
                }  
                //kill player  
                _player.kill();  
                _game.gameData.lives--;                
            }  
            //check collision of hero and bonus character  
            //if bonus character is visible and not on hero  
            if (_bonusFrog.visible) {  
                if (_bonusFrog.bounds().overlaps(_player.bounds())) {  
                    _player.hasBonus = true;   
                }  
            }   
        } else {  
            if (_player.hasBonus) {  
                _bonusFrog.visible = false;  
                _player.hasBonus = false;  
            }  
        }  
          
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
        //render all elements  
        GL20 gl = Gdx.gl;  
        gl.glClearColor(0, 0, 0, 1);  
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
          
        _game.camera.update();  
          
          
        _game.spriteBatch.setProjectionMatrix(_game.camera.combined);  
        _game.spriteBatch.enableBlending();  
        _game.spriteBatch.begin();  
          
        len = elements.size();  
        GameSprite element;  
        for (i = 0; i < len; i++) {  
            element = elements.get(i);  
            if (!element.visible) continue;  
            if (element.skin == null) {  
                element.draw();  
            } else {  
                _game.spriteBatch.draw(element.skin, element.x, element.y);  
            }  
        }  
        _game.spriteBatch.end();  

	}
	
	public void gameOver(){
		_gameOverMsg.visible = true;
		_game.gameData.gameMode = Game.GAME_STATE_PAUSE;
	}
	
	public void targetReached(){
		//show the time needed to reach this target
		
		_levelTimeMsg.timeLabel.value = _timeBar.seconds;
		_levelTimeMsg.show();		
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				_levelTimeMsg.hide();
				if(_game.gameData.targetsReached == 5){
					newLevel();
				}
				timer.cancel();
			}
		}, 3000, 1000);
		
		_player.reset();
		_timeBar.seconds = 0;
	}	
	
	//start new level
	public void newLevel(){
		_game.gameData.gameMode =  Game.GAME_STATE_PAUSE;
		//increase the speeds in the tiers
		_game.gameData.tierSpeed1 += 0.1;
		_game.gameData.tierSpeed2 += 0.2;
		_game.gameData.level++;
		
		_newLevelMsg.show();
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				_newLevelMsg.hide();
				timer.cancel();
			}
		}, 3000, 1000);
		
		//reset the targets reached
		_game.gameData.targetsReached = 0;
		
		for(int i = 0; i < _tiers.size(); i++){
			_tiers.get(i).refresh();
		}
		
		_timeBar.reset();
		_game.gameData.gameMode = Game.GAME_STATE_PLAY;
	}

}
