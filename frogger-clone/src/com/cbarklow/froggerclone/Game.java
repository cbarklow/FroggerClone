package com.cbarklow.froggerclone;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cbarklow.froggerclone.data.GameData;
import com.cbarklow.froggerclone.screens.GameScreen;
import com.cbarklow.froggerclone.screens.MenuScreen;
import com.cbarklow.froggerclone.screens.Screen;

public class Game implements ApplicationListener {
	
	public static final int GAME_STATE_PLAY = 0;
	public static final int GAME_STATE_PAUSE = 1;
	public static final int GAME_STATE_ANIMATE = 2;
	
	public Screen screen;
	
	public GameData gameData;
	public SpriteBatch spriteBatch;
	public OrthographicCamera camera;
	public int screenWidth = 0;
	public int screenHeight = 0;
	
	protected HashMap<String, Screen> _screens;

	@Override
	public void create() {
		_screens = new HashMap<String, Screen>();

	}
	
	public void setScreen(String screenClassName){
		//screenClassName = "com.cbarklow.froggerclone.screens."+ screenClassName;
		Screen newScreen = null;
		if(_screens.containsKey(screenClassName) == false){
			try{
//				Class screenClass = Class.forName(screenClassName);
//				Constructor constructor = screenClass.getConstructor(Game.class);
				if(screenClassName.equals("GameScreen")){
					newScreen = (Screen) new GameScreen(this);
				} else if(screenClassName.equals("MenuScreen")){
					newScreen = (Screen) new MenuScreen(this);
				} else{
					throw new ClassNotFoundException();
				}
				_screens.put(screenClassName, newScreen);
			} catch(ClassNotFoundException ex){
				System.err.println(ex + " Screen class not found.");
			}
		} else {
			newScreen = _screens.get(screenClassName);
		}
		
		if(newScreen == null){
			return;
		}
		
		if(screen != null){
			//remove current screen!
			screen.destroy();
		}
		
		screen = newScreen;
		screen.createScreen();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {				
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub	
	}

}
