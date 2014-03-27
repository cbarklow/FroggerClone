package com.cbarklow.froggerclone.elements;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.screens.GameScreen;
import com.cbarklow.froggerclone.sprites.GameSprite;

public class TimeBar extends GameSprite {
	public int seconds;
	
	private Timer _timer;
	private int _timeWidth;
	private float _timeDecrement;
	private TextureRegion _bar;
	
	public TimeBar(Game game, float x, float y){
		super(game, x, y);
		
		seconds = 0;
		
		skin = null;
		_bar = ImageCache.getTexture("timebar");
		width = _bar.getRegionWidth();
		height = _bar.getRegionHeight();
		
		_timeWidth = width;
		_timeDecrement = _timeWidth * 0.001f;
		
		_timer = new Timer();
		
		_game.screen.elements.add(this);
		_timer.schedule(new TickTockTask(), 01, 1000);
	}
	
	@Override
	public void reset(){
		//reset width
		_timeWidth = width;
		visible = true;
		seconds = 0;
	}
	
	@Override
	public void draw(){
		_game.spriteBatch.draw(_bar, x, y, _timeWidth, height);
	}
	
	class TickTockTask extends TimerTask{
		public void run(){
			if(_game.gameData.gameMode == Game.GAME_STATE_PLAY && visible){
				seconds++;
				if(_timeWidth - _timeDecrement <= 0){
					visible = false;
					_timer.cancel();
					GameScreen screen = (GameScreen) _game.screen;
					screen.gameOver();
				} else {
				_timeWidth -= _timeDecrement;
				}
			}
		}
	}

}
