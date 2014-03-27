package com.cbarklow.froggerclone.screens;

import java.util.ArrayList;
import java.util.List;

import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.sprites.GameSprite;

public abstract class Screen implements com.badlogic.gdx.Screen {
	
	public List<GameSprite> elements;
	protected Game _game;
	
	public Screen(Game game){
		this._game = game;
		elements = new ArrayList<GameSprite>();
	}
	
	public void pause(){
		_game.gameData.gameMode = Game.GAME_STATE_PAUSE;
	};
	public void resume(){
		_game.gameData.gameMode = Game.GAME_STATE_PLAY;
	};
	public void dispose(){};
	public void hide(){};
	public void show(){};
	public void destroy(){};
	
	public abstract void createScreen();
	public abstract void update (float dt);
	
	
	@Override
	public void render(float arg0){
	}
	
	@Override
	public void resize(int arg0, int arg1){
	}

}
