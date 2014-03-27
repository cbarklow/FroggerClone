package com.cbarklow.froggerclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cbarklow.froggerclone.data.GameData;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.data.Sounds;

public class FroggerCloneGame extends Game {
	
	@Override
	public void create(){
		super.create();
		
		camera = new OrthographicCamera(320, 480);
		camera.position.set(320 * 0.5f, 480 * 0.5f, 0);
		
		screenWidth = 320;
		screenHeight = 480;
		
		Sounds.load();
		ImageCache.load();
		
		gameData = new GameData(this);
		spriteBatch = new SpriteBatch();
		
		setScreen("MenuScreen");
	}
	
	@Override
	public void dispose(){
		if(screen != null) screen.pause();
	}
	
	@Override
	public void render(){
		if(screen != null){
			screen.update(Gdx.graphics.getDeltaTime());
		} else {
			GL20 gl = Gdx.gl;
			gl.glClearColor(0,0,0,1);
			gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
	}
	
	@Override
	public void resize(int arg0, int arg1){
		
	}
	
	@Override
	public void pause(){
		if(screen != null) screen.pause();		
	}
	
	@Override
	 public void resume(){
		if(screen != null) screen.resume();
	}

}
