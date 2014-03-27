package com.cbarklow.froggerclone.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.GameSprite;

public class Lives extends GameSprite {
	
	private List<Sprite> _lives;

	public Lives(Game game, float x, float y){
		super(game, x, y);
		
		skin = null;
		
		_lives = new ArrayList<Sprite>();
		
		Sprite sprite;
		for(int i = 0; i < _game.gameData.lives; i++){
			sprite = new Sprite(ImageCache.getTexture("hero_down"));
			sprite.setPosition(x + i * sprite.getRegionWidth() + 5, y);
			_lives.add(sprite);
		}
		
		_game.screen.elements.add(this);
	}
	
	@Override
	public void draw(){
		Sprite sprite;
		for(int i = 0; i < _game.gameData.lives; i++){
			sprite = _lives.get(i);
			sprite.draw(_game.spriteBatch);
		}
	}
}
